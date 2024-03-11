package ua.gov.diia.ui_base.components.atom.space

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.gov.diia.ui_base.components.infrastructure.UIElementData

@Composable
fun SpacerAtm(data: SpacerAtmData) {
    Spacer(
        modifier = Modifier.height(
            when (data.type){
                SpacerAtmType.SPACER_8 -> 8.dp
                SpacerAtmType.SPACER_16 -> 16.dp
                SpacerAtmType.SPACER_24 -> 24.dp
                SpacerAtmType.SPACER_32 -> 32.dp
                SpacerAtmType.SPACER_64 -> 64.dp
            }
        )
    )
}

data class SpacerAtmData(val type: SpacerAtmType): UIElementData

enum class SpacerAtmType {
    SPACER_8, SPACER_16, SPACER_24, SPACER_32, SPACER_64
}