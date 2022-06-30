package com.irfan.sadaparcel.cart

import com.google.common.truth.Truth.assertThat
import com.irfan.sadaparcel.UiState
import org.junit.jupiter.api.Test

class ShoppingCartShould {
  @Test
  fun loadAddedInventoryItems(){
      assertThat(shoppingCartSpyUiController.UiState).isEqualTo(listOf(UiState.ShowLoading,UiState.Success(),UiState.HideLoading))
  }
}