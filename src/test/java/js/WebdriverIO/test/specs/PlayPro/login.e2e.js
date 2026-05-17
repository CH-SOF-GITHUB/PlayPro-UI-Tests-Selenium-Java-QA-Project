const {expect} = require('@wdio/globals')
const LoginPage = require('../../pageobjects/PlayPro/login.page')
const SecurePage = require('../../pageobjects/PlayPro/secure.page')
const CookiePage = require('../../pageobjects/PlayPro/cookie.page')

describe('PlayPro Login System', () => {
    it('should login with valid credentials', async () => {
        await LoginPage.open();
        await CookiePage.acceptCookies();
        await LoginPage.login('demotenant3@yopmail.com', 'Admin1234!');
        await expect(SecurePage.ToastifyAlert).toBeExisting();
        await expect(SecurePage.ToastifyAlert).toHaveText(
            expect.stringContaining('Heureux de vous revoir'))
    })
})
