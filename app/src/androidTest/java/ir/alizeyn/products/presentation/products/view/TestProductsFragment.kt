package ir.alizeyn.products.presentation.products.view

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import ir.alizeyn.products.R
import ir.alizeyn.products.data.network.di.NetworkModule
import ir.alizeyn.products.presentation.detail.DetailFragment
import ir.alizeyn.products.utils.launchFragmentInHiltContainer
import ir.alizeyn.products.utils.withRecyclerView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
@UninstallModules(NetworkModule::class)
@HiltAndroidTest
class TestProductsFragment {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        hiltRule.inject()
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            navController.setGraph(R.navigation.products_nav)
        }
    }

    @Test
    fun testClickOnProductToShowDetails() {

        launchFragmentInHiltContainer<ProductsFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        Espresso.onView(withRecyclerView(R.id.productsRecyclerView).atPosition(0))
            .perform(ViewActions.click())

        Truth.assertThat(
            navController.currentDestination
                ?.displayName
                ?.contains(DetailFragment::class.java.simpleName, true)
        ).isTrue()
    }
}