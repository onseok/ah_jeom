package com.ssac.ah_jeom.src.profile.illustration.illustrationDetail


import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.ssac.ah_jeom.R
import com.ssac.ah_jeom.config.BaseActivity
import com.ssac.ah_jeom.databinding.ActivityIllustrationDetailBinding
import com.ssac.ah_jeom.src.profile.illustration.illustrationDetail.illustrationImages.*

class IllustrationDetailActivity : BaseActivity<ActivityIllustrationDetailBinding>(ActivityIllustrationDetailBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityIllustrationDetailBackButton.setOnClickListener {
            onBackPressed()
        }

        val bottomSheetView = layoutInflater.inflate(R.layout.illustration_dialog, null)
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(bottomSheetView)


        with(binding) {
            activityIllustrationDetailPlatinumImage.setOnClickListener {
                illustrationId = 7
                val image = bottomSheetDialog.findViewById<AppCompatImageView>(R.id.illustration_dialog_image)
                image?.setImageResource(R.drawable.ic_platinum_character)
                val title = bottomSheetDialog.findViewById<TextView>(R.id.illustration_dialog_title)
                title?.text = "백금아식"
                val subTitle = bottomSheetDialog.findViewById<TextView>(R.id.illustration_dialog_sub_title)
                subTitle?.text = "반짝반짝 빛나요\n작품 60개 이상이면 만날수 있어요!"
                val goButton = bottomSheetDialog.findViewById<ConstraintLayout>(R.id.illustration_dialog_go_button)
                goButton?.setOnClickListener {
                    startActivity(Intent(this@IllustrationDetailActivity, PlatinumIllustrationActivity::class.java))
                    overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
                }
                bottomSheetDialog.show()
            }
            activityIllustrationDetailGoldImage.setOnClickListener {
                illustrationId = 6
                val image = bottomSheetDialog.findViewById<AppCompatImageView>(R.id.illustration_dialog_image)
                image?.setImageResource(R.drawable.ic_gold_character)
                val title = bottomSheetDialog.findViewById<TextView>(R.id.illustration_dialog_title)
                title?.text = "골드아식"
                val subTitle = bottomSheetDialog.findViewById<TextView>(R.id.illustration_dialog_sub_title)
                subTitle?.text = "왕이되고 싶어요\n작품 40개 이상이면 만날수 있어요!"
                val goButton = bottomSheetDialog.findViewById<ConstraintLayout>(R.id.illustration_dialog_go_button)
                goButton?.setOnClickListener {
                    startActivity(Intent(this@IllustrationDetailActivity, GoldIllustrationActivity::class.java))
                    overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
                }
                bottomSheetDialog.show()
            }
            activityIllustrationDetailSilverImage.setOnClickListener {
                illustrationId = 5
                val image = bottomSheetDialog.findViewById<AppCompatImageView>(R.id.illustration_dialog_image)
                image?.setImageResource(R.drawable.ic_silver_character)
                val title = bottomSheetDialog.findViewById<TextView>(R.id.illustration_dialog_title)
                title?.text = "실버아식"
                val subTitle = bottomSheetDialog.findViewById<TextView>(R.id.illustration_dialog_sub_title)
                subTitle?.text = "골드아식을 노려요\n작품 30개 이상이면 만날수 있어요!"
                val goButton = bottomSheetDialog.findViewById<ConstraintLayout>(R.id.illustration_dialog_go_button)
                goButton?.setOnClickListener {
                    startActivity(Intent(this@IllustrationDetailActivity, SilverIllustrationActivity::class.java))
                    overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
                }
                bottomSheetDialog.show()
            }
            activityIllustrationDetailStandardImage.setOnClickListener {
                illustrationId = 4
                val image = bottomSheetDialog.findViewById<AppCompatImageView>(R.id.illustration_dialog_image)
                image?.setImageResource(R.drawable.ic_standard_character)
                val title = bottomSheetDialog.findViewById<TextView>(R.id.illustration_dialog_title)
                title?.text = "평타아식"
                val subTitle = bottomSheetDialog.findViewById<TextView>(R.id.illustration_dialog_sub_title)
                subTitle?.text = "반짝거리고 싶어해요\n작품 20개 이상이면 만날수 있어요!"
                val goButton = bottomSheetDialog.findViewById<ConstraintLayout>(R.id.illustration_dialog_go_button)
                goButton?.setOnClickListener {
                    startActivity(Intent(this@IllustrationDetailActivity, StandardIllustrationActivity::class.java))
                    overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
                }
                bottomSheetDialog.show()
            }
            activityIllustrationDetailCopperImage.setOnClickListener {
                illustrationId = 3
                val image = bottomSheetDialog.findViewById<AppCompatImageView>(R.id.illustration_dialog_image)
                image?.setImageResource(R.drawable.ic_copper_character)
                val title = bottomSheetDialog.findViewById<TextView>(R.id.illustration_dialog_title)
                title?.text = "구리아식"
                val subTitle = bottomSheetDialog.findViewById<TextView>(R.id.illustration_dialog_sub_title)
                subTitle?.text = "빛나는 것들을 찾아요\n작품 10개 이상이면 만날수 있어요!"
                val goButton = bottomSheetDialog.findViewById<ConstraintLayout>(R.id.illustration_dialog_go_button)
                goButton?.setOnClickListener {
                    startActivity(Intent(this@IllustrationDetailActivity, CopperIllustrationActivity::class.java))
                    overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
                }
                bottomSheetDialog.show()
            }
            activityIllustrationDetailPooImage.setOnClickListener {
                illustrationId = 2
                val image = bottomSheetDialog.findViewById<AppCompatImageView>(R.id.illustration_dialog_image)
                image?.setImageResource(R.drawable.ic_poo_character)
                val title = bottomSheetDialog.findViewById<TextView>(R.id.illustration_dialog_title)
                title?.text = "응아아식"
                val subTitle = bottomSheetDialog.findViewById<TextView>(R.id.illustration_dialog_sub_title)
                subTitle?.text = "가만히 있는게 좋아요\n작품 1개 이상이면 만날수 있어요!"
                val goButton = bottomSheetDialog.findViewById<ConstraintLayout>(R.id.illustration_dialog_go_button)
                goButton?.setOnClickListener {
                    startActivity(Intent(this@IllustrationDetailActivity, PooIllustrationActivity::class.java))
                    overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
                }
                bottomSheetDialog.show()
            }
            activityIllustrationDetailStoneImage.setOnClickListener {
                illustrationId = 1
                val image = bottomSheetDialog.findViewById<AppCompatImageView>(R.id.illustration_dialog_image)
                image?.setImageResource(R.drawable.ic_stone_character)
                val title = bottomSheetDialog.findViewById<TextView>(R.id.illustration_dialog_title)
                title?.text = "돌멩아식"
                val subTitle = bottomSheetDialog.findViewById<TextView>(R.id.illustration_dialog_sub_title)
                subTitle?.text = "금방 태어나 아무것도 몰라요\n처음 만나는 아이예요!"
                val goButton = bottomSheetDialog.findViewById<ConstraintLayout>(R.id.illustration_dialog_go_button)
                goButton?.setOnClickListener {
                    startActivity(Intent(this@IllustrationDetailActivity, StoneIllustrationActivity::class.java))
                    overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
                }
                bottomSheetDialog.show()
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out)
    }

    companion object {
        var illustrationId = 0
    }
}