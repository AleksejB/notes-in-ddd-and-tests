package com.aleksejb.ui.checkbox.note

import androidx.lifecycle.viewModelScope
import com.aleksejb.core.domain.model.CheckboxItem
import com.aleksejb.core.domain.model.CheckboxNote
import com.aleksejb.core.domain.usecase.checkbox.GetCheckboxNoteUseCase
import com.aleksejb.core.domain.usecase.checkbox.SaveCheckboxNoteUseCase
import com.aleksejb.core.domain.util.noteDoesNotExists
import com.aleksejb.core.ui.viewmodel.MVIViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.*

class CheckboxNoteViewModel(
    private val noteId: Int?,
    private val saveCheckboxNoteUseCase: SaveCheckboxNoteUseCase,
    private val getCheckboxNoteUseCase: GetCheckboxNoteUseCase,
    private val scope: CoroutineScope
): MVIViewModel<CheckboxNoteState, CheckboxNoteEvent, CheckboxNoteEffect>() {

    override val _state = MutableStateFlow(CheckboxNoteState())

    init { getNoteIfExists() }

    fun onDispose() = scope.launch { saveNote() }

    override fun handleEvent(event: CheckboxNoteEvent) {
        when (event) {
            is CheckboxNoteEvent.OnTitleChanged -> updateTitle(event)
            is CheckboxNoteEvent.OnItemNameChanged -> onItemNameChanged(event.itemId, event.input)
            is CheckboxNoteEvent.OnItemChecked -> onItemChecked(event.itemId)
            is CheckboxNoteEvent.OnNewItemClicked -> onNewItemClicked()
        }
    }

    private fun onNewItemClicked() {
        val new = getCheckboxItemsWithNewItem()
        updateItems(new)
    }

    private fun getCheckboxItemsWithNewItem() = state.value.items.plus(
        CheckboxItem(
            id = UUID.randomUUID().toString(),
            itemName = "",
            isComplete = false
        )
    )

    private fun onItemChecked(itemId: String) {
        val new = getNewCheckboxItemsWithNewIsComplete(itemId)
        updateItems(new)
    }

    private fun getNewCheckboxItemsWithNewIsComplete(itemId: String) =
        state.value.items.map { item ->
            if (item.id == itemId) {
                CheckboxItem(
                    id = item.id,
                    itemName = item.itemName,
                    isComplete = !item.isComplete
                )
            } else item
        }

    private fun onItemNameChanged(itemId: String, input: String) {
        val new = getNewCheckboxItemsWithNewName(itemId, input)
        updateItems(new)
    }

    private fun getNewCheckboxItemsWithNewName(itemId: String, input: String) =
        state.value.items.map { item ->
            if (item.id == itemId) {
                CheckboxItem(
                    id = item.id,
                    itemName = input,
                    isComplete = item.isComplete
                )
            } else item
        }

    private fun updateItems(new: List<CheckboxItem>) {
        updateState { copy(items = new) }
    }

    private fun updateTitle(event: CheckboxNoteEvent.OnTitleChanged) {
        updateState { copy(title = event.input) }
    }

    private suspend fun saveNote() {
        saveCheckboxNoteUseCase.invoke(
            CheckboxNote(
                id = noteId,
                title = state.value.title,
                items = state.value.items
            )
        )
    }

    private fun getNoteIfExists() {
        viewModelScope.launch {
            if (noteId.noteDoesNotExists()) return@launch

            getCheckboxNoteUseCase.invoke(noteId!!)?.let {
                updateState { copy(title = it.title, items = it.items) }
            }
        }
    }
}