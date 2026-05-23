const {expect} = require('@wdio/globals')
const RegisterPage = require('../../pageobjects/PlayPro/register.page')
const CookiePage = require('../../pageobjects/PlayPro/cookie.page')
const SecurePage = require('../../pageobjects/PlayPro/secure.page')

describe('PlayPro Register system', () => {
    it('should register with valid credentials', async () => {
        // code of test here
        await RegisterPage.open();
        await CookiePage.acceptCookies();
        await RegisterPage.register("Male", "Ben Said", "Chaker", "cbh01@yopmail.com", "136866895", "Admin1234!");
        // check the expected result compared to the actual result
        await expect(SecurePage.ToastifyAlert).toBeExisting();
        await expect(SecurePage.ToastifyAlert).toHaveText(expect.stringContaining("Bienvenue ! Votre compte est activé."))
    })
})
