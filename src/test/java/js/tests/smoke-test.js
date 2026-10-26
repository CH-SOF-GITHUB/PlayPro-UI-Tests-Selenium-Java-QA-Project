(async () => {
  const response = await fetch('https://www.saucedemo.com');
  console.log(`HTTP status: ${response.status}`);
  if (response.status !== 200) {
    throw new Error(`Expected HTTP 200, got ${response.status}`);
  }
})().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});
