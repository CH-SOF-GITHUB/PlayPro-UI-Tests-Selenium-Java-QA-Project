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
        return $('button[data-testid="cookie-banner-accept-button"]');
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
