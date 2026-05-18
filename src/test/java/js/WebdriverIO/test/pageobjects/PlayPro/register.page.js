const {$} = require('@wdio/globals');
const Page = require('./page');

/**
 * sub page containing specific selectors and methods for a specific page
 */
class RegisterPage extends Page {
    /**
     * define selectors using getter methods
     */
    get checkMale() {
        return $('html > body > div:nth-of-type(1) > main > div:nth-of-type(3) > form > div:nth-of-type(1) > div > label:nth-of-type(1)');
    }

    get checkFemale() {
        return $('html > body > div:nth-of-type(1) > main > div:nth-of-type(3) > form > div:nth-of-type(1) > div > label:nth-of-type(2)');
    }

    get inputNom() {
        return $('input[name="lastName"]');
    }

    get inputPrenom() {
        return $('input[name="firstName"]');
    }

    get inputEmail() {
        return $('input[placeholder=\'Adresse email*\']');
    }

    get inputTelephone() {
        return $('input[type="tel"]');
    }

    get inputDateDeNaissance() {
        return $('input[value="DD/MM/YYYY"]');
    }

    get inputPassword() {
        return $('input[placeholder=\'Mot de passe*\']');
    }

    get checkCGUV() {
        return $('div[class=\'relative w-full flex\']');
    }

    get btnRegister() {
        return $('button[class=\'w-full h-[45px] text-poppins text-medium text-[15px] leading-5 px-8 mb-6 border-black border rounded-[6px] bg-secondary text-accent\']');
    }

    /**
     * a method to encapsule automation code to interact with the page
     * e.g. to login using username and password
     */
    async register(gender, nom, prenom, email, telephone, password) {

        if (gender === "Male") {
            await this.checkMale.click();
        } else if (gender === "Female") {
            await this.checkFemale.click();
        }

        await this.inputNom.setValue(nom);
        await this.inputPrenom.setValue(prenom);
        await this.inputEmail.setValue(email);
        await this.inputTelephone.setValue(telephone);

        // petit scroll manuel
        await browser.execute(() => {
            window.scrollBy(0, 300);
        });

        await browser.pause(1000);

        await this.inputPassword.setValue(password);

        // scroll vers checkbox
        await this.checkCGUV.scrollIntoView();

        await browser.pause(1000);

        await this.checkCGUV.click();

        // scroll vers bouton
        await this.btnRegister.scrollIntoView();

        await browser.pause(2000);

        // JS click pour éviter "click intercepted"
        await browser.execute((btn) => {
            btn.click();
        }, await this.btnRegister);
    }

    /**
     * overwrite specific options to adapt it to page object
     */
    open() {
        return super.open('connexion');
    }
}

module.exports = new RegisterPage();
