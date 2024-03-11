package ua.gov.diia.ui_base.components.molecule.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.gov.diia.ui_base.components.atom.text.textwithparameter.TextParameter
import ua.gov.diia.ui_base.components.atom.text.textwithparameter.TextWithParametersAtom
import ua.gov.diia.ui_base.components.atom.text.textwithparameter.TextWithParametersData
import ua.gov.diia.ui_base.components.infrastructure.UIElementData
import ua.gov.diia.ui_base.components.infrastructure.event.UIAction
import ua.gov.diia.ui_base.components.infrastructure.utils.resource.UiText
import ua.gov.diia.ui_base.components.theme.White

@Composable
fun TextLabelContainerMlc(
    modifier: Modifier = Modifier,
    data: TextLabelContainerMlcData,
    isFirstAtBody: Boolean = false,
    onUIAction: (UIAction) -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .padding(top = if (isFirstAtBody) 8.dp else 24.dp)
            .fillMaxWidth()
            .background(
                color = White, shape = RoundedCornerShape(16.dp)
            )
    ) {
        data.data?.let {
            TextWithParametersAtom(
                modifier = Modifier
                    .padding(16.dp),
                data = it,
                onUIAction = onUIAction
            )
        }
    }
}

data class TextLabelContainerMlcData(
    val data: TextWithParametersData? = null
) : UIElementData

@Composable
@Preview
fun TextLabelContainerMlcPreview() {
    val phoneText =
        UiText.DynamicString("Щоб вирішити це питання, будь ласка, зверніться до ДМС України \nза номером {dmsPhoneNumber}")
    val phoneParameter = TextParameter(
        type = "phone",
        data = TextParameter.Data(
            name = UiText.DynamicString("dmsPhoneNumber"),
            alt = UiText.DynamicString("+38 (044) 278-34-02"),
            resource = UiText.DynamicString("+380442783402"),
        )
    )
    val textWithParametersData =
        TextWithParametersData(text = phoneText, parameters = listOf(phoneParameter))
    val data = TextLabelContainerMlcData(data = textWithParametersData)
    TextLabelContainerMlc(modifier = Modifier, data = data) {}
}