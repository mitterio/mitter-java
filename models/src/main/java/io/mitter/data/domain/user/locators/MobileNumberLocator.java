package io.mitter.data.domain.user.locators;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

/**
 * A user locator that uses phone numbers. It uses the "tele" prefix for string representations, that must be strictly
 * followed by a phone number in the E164 format. Internally it uses a {@link com.google.i18n.phonenumbers.Phonenumber.PhoneNumber}
 * object.
 *
 * <pre>
 * {@code
 * Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse("...", localeCode);
 * MobileNumberLocator mobileNumberLocator = new MobileNumberLocator().setPhoneNumber(phoneNumber);
 *
 * MobileNumberLocator mobileNumberLocator = new MobileNumberLocator().setLocator("tele:+91-999991234");
 * }
 * </pre>
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
@JsonTypeName("tele")
public class MobileNumberLocator extends UserLocator {
    public final static String LOCATOR_SERIALIZED_PREFIX = "tele";
    private final static String INDEPENDENT_TIME_ZONE_IDENTIFIER = "ZZ";

    /**
     * The phone number of the locator
     */
    private Phonenumber.PhoneNumber phoneNumber;
    private final PhoneNumberUtil phoneNumberUtil;

    public MobileNumberLocator(PhoneNumberUtil phoneNumberUtil) {
        super(LOCATOR_SERIALIZED_PREFIX);
        this.phoneNumberUtil = phoneNumberUtil;
    }

    public MobileNumberLocator() {
        this(PhoneNumberUtil.getInstance());
    }

    public Phonenumber.PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public MobileNumberLocator setPhoneNumber(Phonenumber.PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    /**
     * {@inheritDoc}
     * @return The phone number as a string in the E164 format
     */
    @Override
    String getSerializedLocator() {
        return this.phoneNumberUtil.format(this.phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
    }

    /**
     * {@inheritDoc}
     * @param serializedLocator Accepts a phone number in the E164 format
     */
    @Override
    UserLocator setSerializedLocator(String serializedLocator) {
        Phonenumber.PhoneNumber phoneNumber = null;

        try {
            phoneNumber = this.phoneNumberUtil.parse(serializedLocator, INDEPENDENT_TIME_ZONE_IDENTIFIER);
            this.setPhoneNumber(phoneNumber);
            return this;
        } catch (NumberParseException e) {
            throw new IllegalArgumentException("Invalid phone number provided `" + serializedLocator + "`");
        }
    }

    @Override
    public Class<? extends UserLocator> type() {
        return this.getClass();
    }

    @Override
    public String toString() {
        return "MobileNumberLocator{" +
                "phoneNumber=" + phoneNumber +
                '}';
    }
}
