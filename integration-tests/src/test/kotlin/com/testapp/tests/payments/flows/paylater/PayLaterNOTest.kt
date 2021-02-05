package com.testapp.tests.payments.flows.paylater

import com.testapp.network.KlarnaApi
import com.testapp.utils.BillingAddressTestHelper
import com.testapp.utils.SessionHelper
import org.junit.Test

internal class PayLaterNOTest : BasePayLaterTest() {

    @Test
    fun `test payment pay later norway successful flow`() {
        val session = KlarnaApi.getSessionInfo(SessionHelper.getRequestNO())?.session
        testPayLater(true, session, BillingAddressTestHelper.getBillingInfoNO())
    }

    @Test
    fun `test payment pay later norway failure flow`() {
        val session = KlarnaApi.getSessionInfo(SessionHelper.getRequestNO())?.session
        testPayLater(false, session, BillingAddressTestHelper.getBillingInfoNO())
    }
}