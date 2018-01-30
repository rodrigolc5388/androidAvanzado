package madridshops.rodrigolc.com.repository

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.rodrigolc.madridshops.repository.db.buildDBHelper
import com.rodrigolc.madridshops.repository.db.dao.ShopDAO
import com.rodrigolc.madridshops.repository.model.ShopEntity
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    // Context of the app under test.
    val appContext = InstrumentationRegistry.getTargetContext()

    // Connection to DB
    val dbHelper = buildDBHelper(appContext, "mydb.sqlite", 1)

    @Test
    @Throws(Exception::class)
    fun given_valid_shop_it_gets_inserted_correctly() {

        val shop = ShopEntity(
                1,
                1,
                "My shop",
                "",
                1.0f,
                2.0f,
                "",
                "",
                "",
                ""
        )


        val shopEntityDao = ShopDAO(dbHelper)

        val id = shopEntityDao.insert(shop)

        assertTrue(id > 0)
    }
}
