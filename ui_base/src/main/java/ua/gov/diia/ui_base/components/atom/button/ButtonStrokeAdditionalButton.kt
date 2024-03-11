package ua.gov.diia.ui_base.components.atom.button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.gov.diia.ui_base.components.infrastructure.event.UIAction
import ua.gov.diia.ui_base.components.infrastructure.event.UIActionKeysCompose
import ua.gov.diia.ui_base.components.infrastructure.state.UIState
import ua.gov.diia.ui_base.components.infrastructure.utils.resource.UiText
import ua.gov.diia.ui_base.components.subatomic.loader.LoaderCircularEclipse23Subatomic
import ua.gov.diia.ui_base.components.theme.Black
import ua.gov.diia.ui_base.components.theme.BlackAlpha10
import ua.gov.diia.ui_base.components.theme.BlackAlpha30
import ua.gov.diia.ui_base.components.theme.DiiaTextStyle
import ua.gov.diia.ui_base.components.theme.Transparent


@Composable
fun ButtonStrokeAdditionalAtom(
    modifier: Modifier = Modifier,
    data: ButtonStrokeAdditionalAtomData,
    progressIndicator: Pair<String, Boolean> = Pair("", false),
    onUIAction: (UIAction) -> Unit
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Transparent,
            disabledContainerColor = Transparent
        ),
        border = BorderStroke(
            width = 2.dp, color = when (data.interactionState) {
                UIState.Interaction.Disabled -> BlackAlpha10
                UIState.Interaction.Enabled -> Black
            }
        ),
        enabled = data.interactionState == UIState.Interaction.Enabled,
        onClick = {
            onUIAction(UIAction(actionKey = data.actionKey, data = data.id))
        }
    ) {
        AnimatedVisibility(visible = progressIndicator.first == data.id && progressIndicator.second == true) {
            Row {
                LoaderCircularEclipse23Subatomic(modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
        Text(
            text = data.title.asString(), color = when (data.interactionState) {
                UIState.Interaction.Disabled -> BlackAlpha30
                UIState.Interaction.Enabled -> Black
            },
            style = DiiaTextStyle.t2TextDescription,

        )
    }
}

data class ButtonStrokeAdditionalAtomData(
    val actionKey: String = UIActionKeysCompose.BUTTON_REGULAR,
    val id: String = "",
    val title: UiText,
    val interactionState: UIState.Interaction = UIState.Interaction.Enabled
)

@Composable
@Preview
fun ButtonStrokeAdditionalAtomPreview_EnabledState() {
    val buttonStateEnabled = ButtonStrokeAdditionalAtomData(
        title = UiText.DynamicString("Label"),
        id = "",
        interactionState = UIState.Interaction.Enabled
    )
    ButtonStrokeAdditionalAtom(data = buttonStateEnabled) {
    }
}

@Composable
@Preview
fun ButtonStrokeAdditionalAtomPreview_DisabledState() {
    val buttonStateDisabled = ButtonStrokeAdditionalAtomData(
        title = UiText.DynamicString("Label"),
        id = "",
        interactionState = UIState.Interaction.Disabled
    )
    ButtonStrokeAdditionalAtom(data = buttonStateDisabled) {
    }
}

@Composable
@Preview
fun ButtonStrokeAdditionalAtomPreview_LoadingState() {
    val buttonStateLoading = ButtonStrokeAdditionalAtomData(
        title = UiText.DynamicString("Label"),
        id = "id",
        interactionState = UIState.Interaction.Enabled
    )
    ButtonStrokeAdditionalAtom(
        data = buttonStateLoading, progressIndicator = Pair("id", true)
    ) {
    }
}
