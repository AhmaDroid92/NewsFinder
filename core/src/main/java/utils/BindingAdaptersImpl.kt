package utils

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import javax.inject.Inject

class BindingAdaptersImpl @Inject constructor() : utils.BindingAdapters {
    override fun AppCompatImageView.setImageUrl(url: String?) {
        Glide.with(this).load(url).into(this)
    }
}
