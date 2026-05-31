import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('https://ecommerce-playground.lambdatest.io/');
  
  await page.getByRole('button', { name: ' My account' }).hover();
  await page.click("'Login'");
  
  await page.getByRole('textbox', { name: 'E-Mail Address' }).click();
  await page.getByRole('textbox', { name: 'E-Mail Address' }).fill('demolambdatestio@yopmail.com');
  
  await page.getByRole('textbox', { name: 'Password' }).click();
  await page.getByRole('textbox', { name: 'Password' }).fill('Admin123!');

  await page.getByRole('button', { name: 'Login' }).click();
  await expect(page).toHaveURL('https://ecommerce-playground.lambdatest.io/index.php?route=account/account');
  
  await page.getByRole('link', { name: ' Edit your account' }).click();
  await expect(page).toHaveURL('https://ecommerce-playground.lambdatest.io/index.php?route=account/edit');

  await page.getByRole('textbox', { name: 'First Name *' }).click();
  await page.getByRole('textbox', { name: 'First Name *' }).fill('Chtest');
  
  await page.getByRole('button', { name: 'Continue' }).click();

  await page.getByRole('button', { name: ' My account' }).click();
  await expect(page).toHaveURL('https://ecommerce-playground.lambdatest.io/index.php?route=account/account');

  await page.getByRole('link', { name: 'Logout'}).click();
  await expect(page).toHaveURL('https://ecommerce-playground.lambdatest.io/index.php?route=account/logouttt');
});