const {$} = require('@wdio/globals');
const Page = require('./page');


/**
 * sub page containing specific selectors and methods for a specific page
 */
class SecurePage extends Page {
    /**
     * define selectors using getter methods
     */
    get ToastifyAlert() {
        return $('.Toastify__toast');
    }
}

module.exports = new SecurePage();
