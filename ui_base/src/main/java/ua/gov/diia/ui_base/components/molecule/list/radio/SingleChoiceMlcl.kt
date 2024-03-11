package ua.gov.diia.ui_base.components.molecule.list.radio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.gov.diia.ui_base.components.atom.divider.DividerSlimAtom
import ua.gov.diia.ui_base.components.atom.radio.RadioBtnAtm
import ua.gov.diia.ui_base.components.atom.radio.RadioBtnAtmData
import ua.gov.diia.ui_base.components.atom.radio.RadioButtonMode
import ua.gov.diia.ui_base.components.infrastructure.UIElementData
import ua.gov.diia.ui_base.components.infrastructure.event.UIAction
import ua.gov.diia.ui_base.components.infrastructure.event.UIActionKeysCompose
import ua.gov.diia.ui_base.components.infrastructure.state.UIState
import ua.gov.diia.ui_base.components.subatomic.preview.PreviewBase64Icons
import ua.gov.diia.ui_base.components.theme.BlackSqueeze
import ua.gov.diia.ui_base.components.theme.DiiaTextStyle
import ua.gov.diia.ui_base.components.theme.White


@Composable
fun SingleChoiceMlcl(
    modifier: Modifier = Modifier,
    data: SingleChoiceMlclData,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    onUIAction: (UIAction) -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .padding(top = 24.dp)
            .fillMaxWidth()
            .background(
                color = White, shape = shape
            )
    ) {
        data.title?.let {
            Text(
                modifier = Modifier.padding(16.dp),
                text = data.title,
                style = DiiaTextStyle.t3TextBody
            )
            DividerSlimAtom(modifier = Modifier.fillMaxWidth(), color = BlackSqueeze)
        }

        val itemModifier: Modifier = Modifier.padding(16.dp)

        data.options.forEachIndexed { index, item ->
            RadioBtnAtm(
                modifier = itemModifier,
                data = item,
            ) {
                onUIAction(
                    UIAction(
                        actionKey = data.actionKey,
                        data = item.id
                    )
                )
            }
            if (index != data.options.size - 1) {
                DividerSlimAtom(
                    modifier = Modifier.fillMaxWidth(),
                    color = BlackSqueeze
                )
            }
        }
    }
}


data class SingleChoiceMlclData(
    val actionKey: String = UIActionKeysCompose.SINGLE_CHOICE_MLCL,
    val id: String,
    val title: String? = null,
    val options: List<RadioBtnAtmData>
) : UIElementData, Cloneable {

    public override fun clone(): SingleChoiceMlclData {
        return super.clone() as SingleChoiceMlclData
    }

    fun onItemClick(clickedItemId: String?): SingleChoiceMlclData {
        val data = this
        if (clickedItemId == null) return this
        return this.copy(
            options = SnapshotStateList<RadioBtnAtmData>().apply {
                data.options.forEach {
                    if (it.id == clickedItemId) {
                        add(it.onRadioButtonClick())
                    } else {
                        add(it.copy(selectionState = UIState.Selection.Unselected))
                    }
                }
            }
        )
    }

    fun dropSelections(): SingleChoiceMlclData {
        val data = this
        return this.copy(
            options = SnapshotStateList<RadioBtnAtmData>().apply {
                data.options.forEach {
                    add(it.copy(selectionState = UIState.Selection.Unselected))
                }
            }
        )
    }
}


@Composable
@Preview
fun SingleChoiceMlclPreview_General() {
    val source = mutableMapOf(
        "1" to "Option 1", "2" to "Option 2", "3" to "Option 3", "4" to "Option 4"
    )
    val data = SingleChoiceMlclData(id = "",
        title = "Title",
        options = SnapshotStateList<RadioBtnAtmData>().apply {
            source.map {
                add(
                    RadioBtnAtmData(
                        id = it.key,
                        label = it.value,
                        mode = RadioButtonMode.SINGLE_CHOICE,
                        interactionState = UIState.Interaction.Enabled,
                        selectionState = UIState.Selection.Unselected,
                        logoLeft = PreviewBase64Icons.apple,
                        status = "Status"
                    )
                )
            }
        })

    val state = remember {
        mutableStateOf(data)
    }

    SingleChoiceMlcl(modifier = Modifier, data = state.value) {
        state.value = state.value.onItemClick(it.data)
    }
}

@Composable
@Preview
fun SingleChoiceMlclPreview_ResetValue() {
    val source = mutableMapOf(
        "1" to "Option 1", "2" to "Option 2", "3" to "Option 3", "4" to "Option 4"
    )
    val data = SingleChoiceMlclData(id = "",
        title = "Title",
        options = SnapshotStateList<RadioBtnAtmData>().apply {
            source.map {
                add(
                    RadioBtnAtmData(
                        id = it.key,
                        label = it.value,
                        mode = RadioButtonMode.SINGLE_CHOICE,
                        interactionState = UIState.Interaction.Enabled,
                        selectionState = UIState.Selection.Unselected,
                        logoLeft = PreviewBase64Icons.apple,
                        status = "Status"
                    )
                )
            }
        })
    val state = remember {
        mutableStateOf(data)
    }

    Column {
        SingleChoiceMlcl(modifier = Modifier, data = state.value) {
            state.value = state.value.onItemClick(it.data)
        }
        Button(onClick = {
            state.value = state.value.dropSelections()
        }) {
            Text("Reset")
        }
    }

}
