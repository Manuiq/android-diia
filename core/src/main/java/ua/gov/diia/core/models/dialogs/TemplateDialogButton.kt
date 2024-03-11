package ua.gov.diia.core.models.dialogs

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class TemplateDialogButton(
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "icon")
    val icon: String? = null,
    @Json(name = "action")
    val action: String,
    @Json(name = "link")
    val link: String? = null
): Parcelable