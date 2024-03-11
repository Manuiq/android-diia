package ua.gov.diia.ui_base.components.atom.button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.gov.diia.ui_base.components.CommonDiiaResourceIcon
import ua.gov.diia.ui_base.components.DiiaResourceIconProvider
import ua.gov.diia.ui_base.components.infrastructure.DataActionWrapper
import ua.gov.diia.ui_base.components.infrastructure.event.UIAction
import ua.gov.diia.ui_base.components.infrastructure.event.UIActionKeysCompose
import ua.gov.diia.ui_base.components.infrastructure.state.UIState
import ua.gov.diia.ui_base.components.infrastructure.utils.resource.UiIcon
import ua.gov.diia.ui_base.components.infrastructure.utils.resource.UiText
import ua.gov.diia.ui_base.components.noRippleClickable
import ua.gov.diia.ui_base.components.subatomic.icon.UiIconWrapperSubatomic
import ua.gov.diia.ui_base.components.subatomic.loader.LoaderCircularEclipse23Subatomic
import ua.gov.diia.ui_base.components.theme.Black
import ua.gov.diia.ui_base.components.theme.BlackAlpha30
import ua.gov.diia.ui_base.components.theme.DiiaTextStyle

@Composable
fun BtnPlainIconAtm(
    modifier: Modifier = Modifier,
    data: BtnPlainIconAtmData,
    progressIndicator: Pair<String, Boolean> = Pair("", false),
    diiaResourceIconProvider: DiiaResourceIconProvider,
    onUIAction: (UIAction) -> Unit
) {
    val isLoading by remember {
        mutableStateOf(data.id == progressIndicator.first && progressIndicator.second)
    }
    Row(
        modifier = modifier
            .padding(horizontal = 40.dp)
            .noRippleClickable {
                if (data.interactionState == UIState.Interaction.Enabled) {
                    onUIAction(
                        UIAction(
                            actionKey = data.actionKey,
                            data = data.id,
                            action = data.action
                        )
                    )
                }
            }, verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedVisibility(visible = isLoading) {
            Row {
                LoaderCircularEclipse23Subatomic()
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        AnimatedVisibility(visible = !isLoading) {
            Row {
                UiIconWrapperSubatomic(
                    modifier = Modifier.size(20.dp),
                    icon = data.icon,
                    diiaResourceIconProvider = diiaResourceIconProvider
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Text(
            text = data.label.asString(),
            style = DiiaTextStyle.t2TextDescription,
            color = when (data.interactionState) {
                UIState.Interaction.Disabled -> BlackAlpha30
                UIState.Interaction.Enabled -> Black
            }
        )

    }
}

data class BtnPlainIconAtmData(
    val actionKey: String = UIActionKeysCompose.BTN_PLAIN_ICON_ATM,
    val id: String,
    val label: UiText,
    val icon: UiIcon,
    val action: DataActionWrapper? = null,
    val interactionState: UIState.Interaction = UIState.Interaction.Enabled
)

@Preview
@Composable
fun BtnPlainIconAtmPreview_Enabled() {
    val data = BtnPlainIconAtmData(
        id = "123",
        label = UiText.DynamicString("label"),
        icon = UiIcon.DrawableResource(CommonDiiaResourceIcon.MENU.code),
        interactionState = UIState.Interaction.Enabled
    )
    BtnPlainIconAtm(
        modifier = Modifier,
        data = data,
        diiaResourceIconProvider = DiiaResourceIconProvider.forPreview()
    ) {

    }
}

@Preview
@Composable
fun BtnPlainIconAtmPreview_Disabled() {
    val data = BtnPlainIconAtmData(
        id = "123",
        label = UiText.DynamicString("label"),
        icon = UiIcon.DrawableResource(CommonDiiaResourceIcon.MENU.code),
        interactionState = UIState.Interaction.Disabled
    )
    BtnPlainIconAtm(
        modifier = Modifier,
        data = data,
        diiaResourceIconProvider = DiiaResourceIconProvider.forPreview()
    ) {

    }
}

@Preview
@Composable
fun BtnPlainIconAtmPreview_Loading() {
    val data = BtnPlainIconAtmData(
        id = "123",
        label = UiText.DynamicString("label"),
        icon = UiIcon.DrawableResource(CommonDiiaResourceIcon.MENU.code),
        interactionState = UIState.Interaction.Enabled
    )
    BtnPlainIconAtm(
        modifier = Modifier,
        data = data,
        progressIndicator = data.id to true,
        diiaResourceIconProvider = DiiaResourceIconProvider.forPreview()
    ) {

    }
}