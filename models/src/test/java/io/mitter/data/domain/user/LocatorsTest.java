package io.mitter.data.domain.user;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import io.mitter.data.domain.user.locators.MobileNumberLocator;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public class LocatorsTest {
    private final static String TEST_PHONE_NUMBER = "+917259023147";
    private final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    @Test
    public void mobileNumberLocatorSetObject() throws NumberParseException {
        MobileNumberLocator mobileNumberLocator = new MobileNumberLocator(phoneNumberUtil)
                .setPhoneNumber(phoneNumberUtil.parse(TEST_PHONE_NUMBER, "ZZ"));

        Assert.assertEquals(
                mobileNumberLocator.getLocator(),
                MobileNumberLocator.LOCATOR_SERIALIZED_PREFIX + ":" + TEST_PHONE_NUMBER
        );

        Assert.assertEquals(
                phoneNumberUtil.format(mobileNumberLocator.getPhoneNumber(), PhoneNumberUtil.PhoneNumberFormat.E164),
                TEST_PHONE_NUMBER
        );
    }

    @Test
    public void mobileNumberLocatorSetSerializedLocator() throws NumberParseException {
        MobileNumberLocator mobileNumberLocator = (MobileNumberLocator) new MobileNumberLocator(phoneNumberUtil)
                .setLocator(MobileNumberLocator.LOCATOR_SERIALIZED_PREFIX + ":" + TEST_PHONE_NUMBER);

        Assert.assertEquals(
                mobileNumberLocator.getPhoneNumber(),
                phoneNumberUtil.parse(TEST_PHONE_NUMBER, "ZZ")
        );
    }
}
