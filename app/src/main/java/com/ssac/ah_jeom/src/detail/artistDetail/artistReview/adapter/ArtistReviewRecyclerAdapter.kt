package com.ssac.ah_jeom.src.detail.artistDetail.artistReview.adapter

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.databinding.ActivityArtistReviewRecyclerItemBinding
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.ReportReviewActivityView
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.ReportReviewService
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.ArtistReviewRecyclerData
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.GetArtistReviewResponse
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.PostReportReviewResponse
import com.ssac.ah_jeom.src.detail.artistDetail.artistReview.models.ReportReviewRequest



class ArtistReviewRecyclerAdapter(private val context: Context, response: GetArtistReviewResponse) :
    RecyclerView.Adapter<ArtistReviewRecyclerAdapter.PagerViewHolder>(), ReportReviewActivityView {

    var listData = mutableListOf<ArtistReviewRecyclerData>()

    val response = response

    var reportNumber = 0

    interface OnItemClickListener {
        fun onItemClick(v: View, data: ArtistReviewRecyclerData, pos: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArtistReviewRecyclerAdapter.PagerViewHolder {
        val binding = ActivityArtistReviewRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PagerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val data = listData[position]
        holder.setData(data)
    }

    inner class PagerViewHolder(val binding: ActivityArtistReviewRecyclerItemBinding) :
        RecyclerView.ViewHolder
            (binding.root) {

        init {
            itemView.setOnClickListener {
            }
            binding.activityArtistReviewReportText.setOnClickListener { reportDialog() }
        }

        private fun reportWriteDialog() {
            val dialog = Dialog(itemView.context)

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)   //???????????? ??????
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // ??????????????? ?????? ?????? (?????? ????????? background??? ???????????? ???????????? ??????)
            dialog.setContentView(com.ssac.ah_jeom.R.layout.report_dialog_review)
            dialog.setCancelable(true)    // ????????????????????? ?????? ????????? ????????? ??? ????????? ??? ??????

            var reportYesButton: Button? =
                dialog.findViewById(com.ssac.ah_jeom.R.id.report_yes_button) // ????????? dialog??? ??????
            var reportNoButton: Button? = dialog.findViewById(com.ssac.ah_jeom.R.id.report_no_button)


            reportYesButton?.setOnClickListener {
                dialog.dismiss()

                val caption : EditText = dialog.findViewById(com.ssac.ah_jeom.R.id.report_dialog_edit_text)

                val reportReviewRequest = ReportReviewRequest(number = reportNumber, caption = caption.text?.toString())

                val pos = adapterPosition
                ReportReviewService(this@ArtistReviewRecyclerAdapter).tryPostReportReview(response.result.review[pos].reviewId, reportReviewRequest)
            }

            reportNoButton?.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }

        private fun reportDialog() {

            val items = arrayOf(
                "??????/????????? ?????? ???????????? ??????",
                "?????? ?????? ????????? ??????",
                "?????? ?????? ??????",
                "?????? ????????? ??????(?????? ??????/??????)",
                "??????(?????? ??????)"
            )
            var selectedItem: String? = null

            val builder = AlertDialog.Builder(itemView.context, com.ssac.ah_jeom.R.style.MyAlertDialogStyle)
                .setTitle("????????????")
                .setSingleChoiceItems(items, -1) { dialog, which ->
                    selectedItem = items[which]
                    reportNumber = which + 1
                }
                .setPositiveButton("??????") { dialog, which ->
//                    Toast.makeText(itemView.context, "${selectedItem.toString()} is Selected", Toast.LENGTH_SHORT).show()
//                    Toast.makeText(itemView.context, "$reportNumber", Toast.LENGTH_SHORT).show()
                    if (reportNumber == 5) {
                        reportWriteDialog()
                    }
                    else {
                        val reportReviewRequest = ReportReviewRequest(number = reportNumber, caption = null)
                        val pos = adapterPosition
                        ReportReviewService(this@ArtistReviewRecyclerAdapter).tryPostReportReview(response.result.review[pos].reviewId, reportReviewRequest)
                    }
                }
                .show()
        }

        fun setData(data: ArtistReviewRecyclerData) {

            Glide.with(itemView.context).load(data.image).circleCrop()
                .into(binding.activityArtistReviewRecyclerProfileImage)
            binding.activityArtistReviewRecyclerNameText.text = data.name
            binding.activityArtistReviewSampleReviewText.text = data.review

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView, data, pos)


                }
            }
        }

    }

    override fun onPostReportReviewSuccess(response: PostReportReviewResponse) {
        Toast.makeText(context, "????????? ?????????????????????.", Toast.LENGTH_SHORT).show()
    }

    override fun onPostReportReviewFailure(message: String) {
        Toast.makeText(context, "?????? : $message", Toast.LENGTH_SHORT).show()
    }
}
//
//class CustomDialog(private val context: Context) {
//
//    private val builder: AlertDialog.Builder by lazy {
//        AlertDialog.Builder(context).setView(view)
//    }
//
//    private val view: View by lazy {
//        View.inflate(context, R.layout.report_dialog_review, null)
//    }
//
//    private var dialog: AlertDialog? = null
//
//    fun setTitle(@StringRes titleId: Int): CustomDialog {
//        view.titleTextView.text = context.getText(titleId)
//        return this
//    }
//
//    fun setTitle(title: CharSequence): CustomDialog {
//        view.titleTextView.text = title
//        return this
//    }
//
//    fun setMessage(@StringRes messageId: Int): CustomDialog {
//        view.messageTextView.text = context.getText(messageId)
//        return this
//    }
//
//    fun setMessage(message: CharSequence): CustomDialog {
//        view.messageTextView.text = message
//        return this
//    }
//
//    fun setPositiveButton(@StringRes textId: Int, listener: (view: View) -> (Unit)): CustomDialog {
//        view.positiveButton.apply {
//            text = context.getText(textId)
//            setOnClickListener(listener)
//        }
//        return this
//    }
//
//    fun setPositiveButton(text: CharSequence, listener: (view: View) -> (Unit)): CustomDialog {
//        view.positiveButton.apply {
//            this.text = text
//            setOnClickListener(listener)
//        }
//        return this
//    }
//
//    fun setNegativeButton(@StringRes textId: Int, listener: (view: View) -> (Unit)): CustomDialog {
//        view.negativeButton.apply {
//            text = context.getText(textId)
//            this.text = text
//            setOnClickListener(listener)
//        }
//        return this
//    }
//
//    fun setNegativeButton(text: CharSequence, listener: (view: View) -> (Unit)): CustomDialog {
//        view.negativeButton.apply {
//            this.text = text
//            setOnClickListener(listener)
//        }
//        return this
//    }
//
//    fun create() {
//        dialog = builder.create()
//    }
//
//    fun show() {
//        dialog = builder.create()
//        dialog?.show()
//    }
//
//    fun dismiss() {
//        dialog?.dismiss()
//    }
//}