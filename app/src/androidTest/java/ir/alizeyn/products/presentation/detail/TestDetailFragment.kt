package ir.alizeyn.products.presentation.detail

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import ir.alizeyn.products.R
import ir.alizeyn.products.data.network.di.NetworkModule
import ir.alizeyn.products.presentation.products.model.ProductUiModel
import ir.alizeyn.products.utils.launchFragmentInHiltContainer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
@MediumTest
@UninstallModules(NetworkModule::class)
@HiltAndroidTest
class TestDetailFragment {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testBackToProductsFragment() {

        val navController = Mockito.mock(NavController::class.java)

        val firstProductUiModelClicked = ProductUiModel(
            "", "", "", "", "", ""
        )
        launchFragmentInHiltContainer<DetailFragment>(
            fragmentArgs = Bundle().apply {
                putParcelable("product", firstProductUiModelClicked)
            }) {
            Navigation.setViewNavController(requireView(), navController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.close))
            .perform(ViewActions.click())

        Mockito.verify(navController).popBackStack()
    }
}