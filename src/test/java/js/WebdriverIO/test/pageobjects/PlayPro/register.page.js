const {$} = require('@wdio/globals');
const Page = require('./page');

/**
 * sub page containing specific selectors and methods for a specific page
 */
class RegisterPage extends Page {
    /**
     * define selectors using getter methods
     */
    // div.w-3.h-3.rounded-full.bg-white
    // html > body > div:nth-of-type(1) > main > div:nth-of-type(3) > form > div:nth-of-type(1) > div > label:nth-of-type(1)
    get checkMale() {
        return $('html > body > div:nth-of-type(1) > main > div:nth-of-type(3) > form > div:nth-of-type(1) > div > label:nth-of-type(1)');
    }

    // ...
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

    // input.form-control
    // input[type="tel"]
    get inputTelephone() {
        return $('input.form-control');
    }

    // button.MuiButtonBase-root.MuiIconButton-root.MuiIconButton-edgeEnd.MuiIconButton-sizeMedium.css-slyssw
    get inputDateDeNaissance() {
        return $('input[value="DD/MM/YYYY"]');
    }

    get inputPassword() {
        return $('input[placeholder=\'Mot de passe*\']');
    }

    // div.absolute.shrink-0
    // label.flex.items-start.gap-4.cursor-pointer.text-base.font-medium.overflow-auto
    // div[class='relative w-full flex']
    get checkCGUV() {
        return $('label.flex.items-start.gap-4.cursor-pointer.text-base.font-medium.overflow-auto');
    }

    // button[class='w-full h-[45px] text-poppins text-medium text-[15px] leading-5 px-8 mb-6 border-black border rounded-[6px] bg-secondary text-accent']
    // div[3] > form > button  ||   div[3]/form/button
    // button[(text() = 'Créer mon compte' or . = 'Créer mon compte')]
    // button[@type='button'])[7]
    // body > div:nth-child(45) > main > div:nth-child(3) > form > button
    get btnRegister() {
        return $('button[class*=\'mb-6\']');
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
        await browser.pause(3000);
        await this.inputPassword.setValue(password);

        // scroll vers checkbox
        await browser.pause(2000);
        await this.checkCGUV.click();

        // scroll vers bouton
        await browser.pause(3000);
        // JS click pour éviter "click intercepted"
        await this.btnRegister.click();
    }

    /**
     * overwrite specific options to adapt it to page object
     */
    open() {
        return super.open('connexion');
    }
}

module.exports = new RegisterPage();
