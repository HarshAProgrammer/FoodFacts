package openfoodfacts.rackluxury.scrachx.openfood.utils

import openfoodfacts.rackluxury.scrachx.openfood.utils.NavigationDrawerListener.NavigationDrawerType

/*
 Created by Lobster on 06.03.18.
*/
interface INavigationItem {
    val navigationDrawerListener: NavigationDrawerListener?

    @NavigationDrawerType
    fun getNavigationDrawerType(): Int
}