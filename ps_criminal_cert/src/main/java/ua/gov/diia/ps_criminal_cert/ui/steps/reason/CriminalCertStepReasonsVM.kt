package ua.gov.diia.ps_criminal_cert.ui.steps.reason

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import ua.gov.diia.core.di.data_source.http.AuthorizedClient
import ua.gov.diia.core.models.ContextMenuField
import ua.gov.diia.ps_criminal_cert.models.response.CriminalCertReasons
import ua.gov.diia.core.models.rating_service.RatingRequest
import ua.gov.diia.ps_criminal_cert.ui.CriminalCertConst
import ua.gov.diia.core.util.delegation.WithContextMenu
import ua.gov.diia.core.util.delegation.WithErrorHandling
import ua.gov.diia.core.util.delegation.WithRatingDialog
import ua.gov.diia.core.util.delegation.WithRetryLastAction
import ua.gov.diia.core.util.event.UiDataEvent
import ua.gov.diia.core.util.extensions.lifecycle.asLiveData
import ua.gov.diia.core.util.extensions.vm.executeAction
import ua.gov.diia.ps_criminal_cert.network.ApiCriminalCert
import ua.gov.diia.publicservice.helper.PSNavigationHelper

@HiltViewModel
class CriminalCertStepReasonsVM @Inject constructor(
    @AuthorizedClient private val api: ApiCriminalCert,
    private val errorHandlingDelegate: WithErrorHandling,
    private val contextMenuDelegate: WithContextMenu<ContextMenuField>,
    private val retryActionDelegate: WithRetryLastAction,
    private val withRatingDialog: WithRatingDialog,
    private val navigationHelper: PSNavigationHelper,
) : ViewModel(), WithErrorHandling by errorHandlingDelegate,
    WithContextMenu<ContextMenuField> by contextMenuDelegate,
    WithRetryLastAction by retryActionDelegate,
    WithRatingDialog by withRatingDialog,
    PSNavigationHelper by navigationHelper {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading.asLiveData()

    private val _isNextAvailable = MutableLiveData(false)
    val isNextAvailable = _isNextAvailable.asLiveData()

    private val _onNextEvent = MutableLiveData<UiDataEvent<String>>()
    val onNextEvent = _onNextEvent.asLiveData()

    private val _state = MutableLiveData<CriminalCertReasons>()
    val state = _state.asLiveData()

    fun loadContent() {
        executeAction(progressIndicator = _isLoading) {
            api.getCriminalCertReasons().also { res ->
                _state.value = CriminalCertReasons(
                    title = res.title,
                    subtitle = res.subtitle,
                    reasons = res.reasons?.map { reason ->
                        CriminalCertReasons.Reason(
                            code = reason.code,
                            name = reason.name,
                            isSelected = state.value?.reasons?.find { it.code == reason.code }?.isSelected == true
                        )
                    }
                )
                res.template?.apply(::showTemplateDialog)
            }
        }
    }

    fun selectReason(reason: CriminalCertReasons.Reason) {
        _state.value = _state.value?.copy(
            reasons = _state.value?.reasons?.map {
                if (it.code == reason.code && !it.isSelected) {
                    it.copy(isSelected = true)
                } else if (it.isSelected) {
                    it.copy(isSelected = false)
                } else {
                    it
                }
            }.orEmpty()
        )
        _isNextAvailable.value = findSelected() != null
    }

    fun onNext() {
        findSelected()?.also { selectedReason ->
            _onNextEvent.value = UiDataEvent(selectedReason.code)
        }
    }

    private fun findSelected(): CriminalCertReasons.Reason? {
        return _state.value?.reasons?.find { it.isSelected }
    }

    fun getRatingForm() {
        getRating(
            category = CriminalCertConst.RATING_SERVICE_CATEGORY,
            serviceCode = CriminalCertConst.RATING_SERVICE_CODE
        )
    }

    fun sendRatingRequest(ratingRequest: RatingRequest) {
        sendRating(
            ratingRequest = ratingRequest,
            category = CriminalCertConst.RATING_SERVICE_CATEGORY,
            serviceCode = CriminalCertConst.RATING_SERVICE_CODE
        )
    }
}