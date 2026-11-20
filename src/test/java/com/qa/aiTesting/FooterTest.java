package com.qa.aiTesting;

import org.testng.Assert;
import org.testng.annotations.Test;

/** Checks the inventory footer content, external links, and persistence. */
public class FooterTest extends SwagLabsTestBase {

    @Test(description = "Inventory footer is visible and includes its copyright")
    public void footerShowsCopyright() {
        loginAsStandardUser();
        footerPage.scrollToFooter();

        Assert.assertTrue(footerPage.isVisible(), "Inventory footer should be visible at the bottom of the page");
        String footerText = footerPage.getCopyrightText();
        Assert.assertTrue(footerText.contains("2026 Sauce Labs"), "Copyright should identify Sauce Labs and the current year: " + footerText);
        Assert.assertTrue(footerText.contains("All Rights Reserved"), "Copyright should include All Rights Reserved: " + footerText);
    }

    @Test(description = "Social footer links are visible, valid, and open their destinations")
    public void socialLinksAreVisibleAndNavigable() {
        loginAsStandardUser();
        footerPage.scrollToFooter();
        assertSocialLink("X", "x.com", "twitter.com");
        assertSocialLink("Facebook", "facebook.com");
        assertSocialLink("LinkedIn", "linkedin.com");
    }

    @Test(description = "Legal footer links are clickable and expose valid destinations")
    public void legalLinksAreClickable() {
        loginAsStandardUser();
        footerPage.scrollToFooter();

        assertLegalLink("Terms of Service");
        assertLegalLink("Privacy Policy");
    }

    @Test(description = "Footer remains available after inventory interaction")
    public void footerPersistsAfterInventoryAction() {
        loginAsStandardUser();
        footerPage.scrollToFooter();
        String originalCopyright = footerPage.getCopyrightText();

        inventoryPage.addProduct("sauce-labs-backpack", "1");
        footerPage.scrollToFooter();

        Assert.assertTrue(footerPage.isVisible(), "Footer should remain visible after adding an inventory item");
        Assert.assertEquals(footerPage.getCopyrightText(), originalCopyright, "Footer copyright should remain consistent after the inventory action");
        Assert.assertTrue(footerPage.getSocialLinkHref("X").startsWith("https://"), "Social links should remain available after the inventory action");
    }

    private void assertSocialLink(String network, String... expectedDomains) {
        String href = footerPage.getSocialLinkHref(network);
        Assert.assertTrue(href != null && href.startsWith("https://"), network + " should have a valid HTTPS href: " + href);

        boolean expectedDomainFound = false;
        for (String domain : expectedDomains) {
            // expectedDomainFound should equal to expectedDomainFound OR href.toLowerCase().contains(domain)
            expectedDomainFound |= href.toLowerCase().contains(domain);
        }
        Assert.assertTrue(expectedDomainFound, network + " href should use its expected domain: " + href);
        Assert.assertTrue(footerPage.clickSocialLink(network), network + " should open a tab or navigate to its href: " + href);
    }

    private void assertLegalLink(String linkText) {
        String href = footerPage.getLegalLinkHref(linkText);
        Assert.assertTrue(href != null && href.startsWith("https://"), linkText + " should have a valid HTTPS href: " + href);
        footerPage.clickLegalLink(linkText);
    }
}
