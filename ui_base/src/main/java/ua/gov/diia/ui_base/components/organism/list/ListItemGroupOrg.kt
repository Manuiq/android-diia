package ua.gov.diia.ui_base.components.organism.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.gov.diia.ui_base.components.CommonDiiaResourceIcon
import ua.gov.diia.ui_base.components.DiiaResourceIconProvider
import ua.gov.diia.ui_base.components.atom.divider.DividerSlimAtom
import ua.gov.diia.ui_base.components.conditional
import ua.gov.diia.ui_base.components.infrastructure.UIElementData
import ua.gov.diia.ui_base.components.infrastructure.event.UIAction
import ua.gov.diia.ui_base.components.infrastructure.event.UIActionKeysCompose
import ua.gov.diia.ui_base.components.infrastructure.state.UIState
import ua.gov.diia.ui_base.components.infrastructure.utils.resource.UiIcon
import ua.gov.diia.ui_base.components.infrastructure.utils.resource.UiText
import ua.gov.diia.ui_base.components.molecule.list.ListItemMlc
import ua.gov.diia.ui_base.components.molecule.list.ListItemMlcData
import ua.gov.diia.ui_base.components.theme.Black
import ua.gov.diia.ui_base.components.theme.BlackSqueeze
import ua.gov.diia.ui_base.components.theme.DiiaTextStyle
import ua.gov.diia.ui_base.components.theme.White

@Composable
fun ListItemGroupOrg(
    modifier: Modifier = Modifier,
    data: ListItemGroupOrgData,
    progressIndicator: Pair<String, Boolean> = Pair("", false),
    diiaResourceIconProvider: DiiaResourceIconProvider,
    onUIAction: (UIAction) -> Unit

) {
    var isLoading by remember {
        mutableStateOf(
            checkIfAnyElementInLoadingState(
                data.itemsList,
                progressIndicator
            )
        )
    }

    LaunchedEffect(key1 = progressIndicator) {
        isLoading =
            checkIfAnyElementInLoadingState(data.itemsList, progressIndicator)
    }

    Column(
        modifier = modifier
            .padding(top = 16.dp, start = 24.dp, end = 24.dp)
            .fillMaxWidth()
            .background(color = White, shape = RoundedCornerShape(16.dp))
    ) {

        data.title?.let {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .conditional(data.itemsList.none {
                        it.interactionState == UIState.Interaction.Enabled
                    }) {
                        alpha(0.3f)
                    },
                text = data.title.asString(),
                style = DiiaTextStyle.t3TextBody,
                color = Black
            )
            DividerSlimAtom(color = BlackSqueeze)
        }

        data.itemsList.forEachIndexed { index, item ->
            ListItemMlc(
                data = item.copy(
                    interactionState = if (isLoading) {
                        if (progressIndicator.first == item.id) {
                            item.interactionState
                        } else {
                            UIState.Interaction.Disabled
                        }
                    } else {
                        item.interactionState
                    }
                ),
                progressIndicator = progressIndicator,
                onUIAction = {
                    onUIAction(
                        UIAction(
                            actionKey = data.actionKey,
                            data = it.data,
                            optionalId = data.id,
                            action = it.action
                        )
                    )
                },
                diiaResourceIconProvider = diiaResourceIconProvider,
            )
            if (index != data.itemsList.size - 1) {
                DividerSlimAtom(color = BlackSqueeze)
            }
        }
    }
}

private fun checkIfAnyElementInLoadingState(
    items: List<ListItemMlcData>,
    progressIndicator: Pair<String, Boolean>
): Boolean {
    return items.any {
        it.id == progressIndicator.first && progressIndicator.second
    }
}

data class ListItemGroupOrgData(
    val actionKey: String = UIActionKeysCompose.LIST_ITEM_GROUP_ORG,
    val id: String? = null,
    val itemsList: List<ListItemMlcData>,
    val title: UiText? = null
) : UIElementData


@Composable
@Preview
fun ListItemGroupOrgPreview() {
    val state = ListItemGroupOrgData(
        title = UiText.DynamicString("title:"),
        itemsList = SnapshotStateList<ListItemMlcData>().apply {
            repeat(4) {
                add(
                    ListItemMlcData(
                        id = it.toString(),
                        label = UiText.DynamicString("Label"),
                        iconLeft = UiIcon.DrawableResource(
                            CommonDiiaResourceIcon.MENU.code
                        )
                    )
                )
            }
        })
    ListItemGroupOrg(
        data = state,
        diiaResourceIconProvider = DiiaResourceIconProvider.forPreview(),
    ) {
    }
}

@Composable
@Preview
fun ListItemGroupOrgPreview_Disabled() {
    val state = ListItemGroupOrgData(
        title = UiText.DynamicString("title:"),
        itemsList = SnapshotStateList<ListItemMlcData>().apply {
            repeat(4) {
                add(
                    ListItemMlcData(
                        id = it.toString(),
                        label = UiText.DynamicString("Label"),
                        iconLeft = UiIcon.DrawableResource(
                            CommonDiiaResourceIcon.MENU.code
                        ),
                        interactionState = UIState.Interaction.Disabled
                    )
                )
            }
        })
    ListItemGroupOrg(
        data = state,
        diiaResourceIconProvider = DiiaResourceIconProvider.forPreview(),
    ) {
    }
}

@Composable
@Preview
fun ListItemGroupOrgPreview_Disabled_and_Enabled() {
    val state = ListItemGroupOrgData(
        title = UiText.DynamicString("title:"),
        itemsList = SnapshotStateList<ListItemMlcData>().apply {
            add(
                ListItemMlcData(
                    id = "999",
                    label = UiText.DynamicString("Label"),
                    iconLeft = UiIcon.DrawableResource(CommonDiiaResourceIcon.MENU.code),
                    interactionState = UIState.Interaction.Enabled
                )
            )
            repeat(4) {
                add(
                    ListItemMlcData(
                        id = it.toString(),
                        label = UiText.DynamicString("Label"),
                        iconLeft = UiIcon.DrawableResource(
                            CommonDiiaResourceIcon.MENU.code
                        ),
                        interactionState = UIState.Interaction.Disabled
                    )
                )
            }
        })
    ListItemGroupOrg(
        data = state,
        diiaResourceIconProvider = DiiaResourceIconProvider.forPreview(),
    ) {
    }
}

@Composable
@Preview
fun ListItemGroupOrgPreview_Loading() {
    val isLoading by remember {
        mutableStateOf(false)
    }
    val loadingItemId = "999"

    var progressIndicator by remember {
        mutableStateOf(loadingItemId to isLoading)
    }

    val state = ListItemGroupOrgData(
        title = UiText.DynamicString("title:"),
        itemsList = SnapshotStateList<ListItemMlcData>().apply {
            add(
                ListItemMlcData(
                    id = "2",
                    label = UiText.DynamicString("Label"),
                    iconLeft = UiIcon.DrawableResource(CommonDiiaResourceIcon.MENU.code),
                    interactionState = UIState.Interaction.Disabled
                )
            )
            add(
                ListItemMlcData(
                    id = loadingItemId,
                    label = UiText.DynamicString("Label"),
                    iconLeft = UiIcon.DrawableResource(CommonDiiaResourceIcon.MENU.code),
                    interactionState = UIState.Interaction.Enabled
                )
            )
            add(
                ListItemMlcData(
                    id = "1",
                    label = UiText.DynamicString("Label"),
                    iconLeft = UiIcon.DrawableResource(CommonDiiaResourceIcon.MENU.code),
                    interactionState = UIState.Interaction.Enabled
                )
            )
        })

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ListItemGroupOrg(
            data = state,
            progressIndicator = progressIndicator,
            diiaResourceIconProvider = DiiaResourceIconProvider.forPreview(),
        ) {
        }
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = {
            progressIndicator =
                progressIndicator.copy(second = !progressIndicator.second)
        }) {
            Text("Change loading state")
        }
    }
}