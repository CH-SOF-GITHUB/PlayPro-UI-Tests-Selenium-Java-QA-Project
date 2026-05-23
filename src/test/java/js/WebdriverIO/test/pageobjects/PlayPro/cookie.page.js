const { $ } = require('@wdio/globals');
const Page = require('./page')


/**
 * sub page containing specific selectors and methods for a specific page
 */
class CookiePage extends Page {
    /**
     * define selectors using getter methods
     */

    get btnAccept() {
        return $('body > div.c15t-root-tnx7V.c15t-bottomLeft-wv7ez.c15t-bannerVisible-bty7Y > div > div > div.desktop-footer.desktop-footer.desktop-footer.c15t-footer-wS150 > div > button.c15t-button-YKOgW.c15t-button-small-n5LJg.c15t-button-primary-stroke-TWzjH');
    }
    /**
     * a method to encapsule automation code to interact with the page
     * e.g. to login using username and password
     */
    async acceptCookies() {
        await this.btnAccept.click();
    }
}

module.exports = new CookiePage();
