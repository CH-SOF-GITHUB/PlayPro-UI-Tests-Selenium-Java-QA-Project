# Automation Practice Page — Test Plan

## Scope and starting state

Target: [SQA Tools Automation Practice Page](https://sqatools.in/automation-practice-page/). The Playwright seed is `tests/seed.spec.ts`; its only setup action is to navigate to this URL. Each scenario below starts with a fresh page load and independent browser state unless the scenario explicitly describes a sequence. This plan covers the practice widgets rendered on the page; navigation to external sites is limited to verifying link behavior and should be run in an isolated tab/context.

The page includes text inputs and address, radio buttons, checkboxes, single and multi-select dropdowns, buttons, JavaScript alerts, file upload, date/time inputs, links, a data table, iframe, hidden and enabled/disabled fields, hover interaction, auto-suggestions, simulated AJAX outcomes, stale-element replacement, drag and drop, keyboard input, and nested Shadow DOM content. Widget labels/options and resulting messages should be recorded during execution because the page's visible text does not expose all option values or response copy.

## Scenarios

### AP-01 — Load the practice page and inspect its primary sections

**Starting state:** Fresh browser context; no cookies or prior form interaction.

1. Navigate to the URL in the seed.
2. Wait for the document to finish loading and inspect the page title and main heading.
3. Scroll from top to bottom through the page.

**Expected:** The page loads without a browser error; the heading “Automation Practice Page” and its widget sections are present. The “Ultimate Automation Practice Page” section appears lower down, and scrolling reaches the bottom content. Fail if the page is blank, key sections are missing, or a blocking error prevents interaction.

### AP-02 — Enter and edit text fields and address

**Starting state:** Fresh page; username, password, and address are blank.

1. Enter a normal username and password in the corresponding fields.
2. Enter a multiline address if the address control supports line breaks.
3. Replace each value, then clear the fields.
4. Repeat with punctuation, non-ASCII characters, and long input.

**Expected:** Values appear in the correct controls, password characters are masked if it is a password input, edits and clearing work, and text remains in the intended field. Record any length limits or normalization. Fail if input is lost, shown in the wrong field, or unexpectedly interpreted as markup.

### AP-03 — Select one radio option at a time

**Starting state:** Fresh page; no radio option selected.

1. Select Male and verify its selected state.
2. Select Female, then Other, checking the state after each action.
3. Reload and verify the initial state.

**Expected:** Exactly one option in the radio group is selected at once; choosing another moves selection; reload restores the initial state. Fail if multiple radio choices remain selected or none can be selected.

### AP-04 — Toggle language and tool checkboxes

**Starting state:** Fresh page; Java, Python, and Selenium are unchecked.

1. Check Java, then Python, then Selenium.
2. Uncheck Python and verify the other two remain checked.
3. Toggle all options off and reload.

**Expected:** Each checkbox toggles independently and preserves other selections; reload returns to the initial state. Fail if toggling one option changes another unexpectedly.

### AP-05 — Use the single-select dropdown

**Starting state:** Fresh page; dropdown at its default selection.

1. Open the dropdown and inspect all available options.
2. Select each valid option in turn and verify the displayed selection.
3. If a placeholder/default option is available, restore it.

**Expected:** The chosen value is displayed and remains selected until changed or page reload. Record options and any dependent behavior. Fail if an option cannot be selected or the display does not reflect the chosen value.

### AP-06 — Use and clear the multi-select dropdown

**Starting state:** Fresh page; multi-select at its default selection.

1. Inspect available options.
2. Select two or more distinct values using the control's supported selection method.
3. Deselect one value, then clear all selections if supported.
4. Reload the page.

**Expected:** Multiple values can be selected simultaneously, individual selections can be removed, and reload restores the default. Fail if selection of a second value replaces the first when multi-selection is supported.

### AP-07 — Exercise Normal Button, Submit, and Reset

**Starting state:** Fresh page; form values and selections blank/default.

1. Click Normal Button with no values entered and observe feedback and page state.
2. Enter representative text, select a radio option and checkboxes, and choose dropdown values.
3. Click Submit and inspect validation, feedback, URL, and field values.
4. Change several values and click Reset.

**Expected:** Each button has a distinct, stable action; Submit handles both empty and populated form state as designed; Reset restores the form to its initial state. Record actual feedback. Fail if reset leaves changed values behind or button actions cause an unhandled error.

### AP-08 — Handle JavaScript simple, confirm, and prompt alerts

**Starting state:** Fresh page; no dialog open.

1. Click Simple Alert and record the dialog type and message; accept it.
2. Click Confirm Alert; test both Accept and Dismiss in separate fresh runs.
3. Click Prompt Alert; test entering a normal value and accepting, then dismissing without a value in a separate run.

**Expected:** The relevant native dialog appears and can be handled; the page responds appropriately to acceptance, dismissal, and prompt input without becoming blocked. Record exact resulting page text. Fail if the dialog cannot be closed or the page remains unusable afterward.

### AP-09 — Upload a file and test replacement

**Starting state:** Fresh page; upload control has no file selected. Use a small harmless fixture file.

1. Select a supported file and inspect the selected filename and any response.
2. Replace it with another file.
3. If file type or size restrictions are indicated, try one disallowed type and one boundary-size file.

**Expected:** A supported file is accepted and visibly identified; replacement updates the selected file. Invalid files are rejected or reported clearly if validation exists. Fail if the visible filename disagrees with the selected file or the control becomes unusable.

### AP-10 — Enter date and time values

**Starting state:** Fresh page; all three date/time inputs at their initial state.

1. Identify the input type and format for each control.
2. Enter a valid date/time value appropriate to each type.
3. Try an invalid/impossible date and boundary values (including the minimum/maximum if constraints are exposed).
4. Clear the fields and reload.

**Expected:** Valid values are accepted in the expected format; invalid values are rejected, constrained, or surfaced through validation; clear and reload restore the initial state. Record constraints and timezone behavior. Fail if accepted values silently change to a different date/time.

### AP-11 — Verify page navigation links and scrolling link

**Starting state:** Fresh page at top.

1. Activate “Go to Bottom” and verify the viewport moves to the bottom section.
2. Verify the “Open Google” link target without leaving the test context; open it in an isolated tab if it navigates externally.
3. Inspect internal navigation links such as Dummy Page and Login Page, and confirm their targets load when followed in isolated contexts.

**Expected:** The bottom link reaches the bottom content, external link targets are correct, and internal links navigate to their intended pages. Fail on broken links or unexpected same-tab navigation that loses the practice page when a new tab is intended.

### AP-12 — Read and validate the web table

**Starting state:** Fresh page.

1. Locate the Web Table and inspect its headers and rows.
2. Verify the displayed records: IDs 1–3 and the associated names and roles.
3. Check that each row's cells align with the correct headers.

**Expected:** The table presents ID, Name, and Role columns and the displayed records are readable and aligned. Fail if records are missing, duplicated, or mapped to the wrong columns.

### AP-13 — Interact with the iframe

**Starting state:** Fresh page; iframe content not yet interacted with.

1. Locate the iframe and inspect its loaded URL/content.
2. Verify that its content is reachable within the frame, not confused with the parent page.
3. If the frame offers an interactive element, activate it and observe the result.

**Expected:** The iframe loads its intended content independently; frame interaction does not target similarly named parent-page elements. Record any cross-origin limitations. Fail if the frame is empty or blocks expected interaction without a clear external restriction.

### AP-14 — Confirm hidden and enabled/disabled field behavior

**Starting state:** Fresh page; do not alter page styles or scripts.

1. Inspect the Hidden Element control using normal user-visible interaction only.
2. Verify that it is not visible or actionable if intentionally hidden.
3. Inspect the Enabled & Disabled Fields section and attempt to focus and type into each field.

**Expected:** The hidden field is not exposed as a normal visible control; the enabled field accepts input while the disabled field cannot be edited. Fail if a disabled control accepts changes or enabled state is presented incorrectly.

### AP-15 — Test hover interaction

**Starting state:** Fresh page; pointer away from the hover target.

1. Record the initial state of “Hover Over Me”.
2. Move the pointer over the button and inspect any revealed content or style change.
3. Move the pointer away and verify the original state returns.

**Expected:** Hover behavior appears on pointer entry and reverses on exit; the control remains operable. Record exact revealed text if present. Fail if the hover-only content cannot be reached or remains stuck after pointer exit.

### AP-16 — Use the auto-suggestion search field

**Starting state:** Fresh page; Search field blank and suggestion list closed.

1. Enter a prefix that produces suggestions and inspect the results.
2. Add characters to narrow the list, then erase them.
3. Select a suggestion with the pointer and, in a separate run, with the keyboard.
4. Enter a query with no matches and inspect empty-state behavior.

**Expected:** Suggestions correspond to the entered prefix and update as input changes; selection populates or submits the expected value; no-match input is handled without stale suggestions. Fail if suggestions are unrelated or cannot be selected.

### AP-17 — Exercise AJAX success and simulated network failure

**Starting state:** Fresh page; no previous result displayed.

1. Click Load Success and wait for its response area to settle.
2. Record successful status/message and verify repeated activation behavior.
3. Reload for an independent run, click Simulate Network Failure, and wait for error handling.

**Expected:** Success produces a success result; the simulated failure produces a readable failure state rather than a hanging spinner or uncaught page error. The two actions are distinguishable and repeatable. Fail if the wrong outcome appears or the page remains permanently busy.

### AP-18 — Recover from stale-element replacement

**Starting state:** Fresh page; replacement control has not been activated.

1. Inspect the “Click to Replace Me” control and nearby response area.
2. Activate it once and locate the replacement control/content.
3. Activate the replacement if it remains actionable and verify the final result.

**Expected:** The original element is replaced as intended, the new element is discoverable and usable, and the UI reflects the action without duplicate/stale content. Fail if the replacement cannot be interacted with or repeated clicks corrupt the section.

### AP-19 — Drag and drop the draggable item

**Starting state:** Fresh page; “Drag Me” is in its source position and “Drop Here” is empty.

1. Drag the item into the target drop area.
2. Verify the target's resulting state and the item's position.
3. Reload and repeat with a drop attempt outside the target, if the page supports it.

**Expected:** A valid drop triggers the expected target state; an invalid drop does not falsely report success. Fail if the item cannot be dragged or the target does not register a valid drop.

### AP-20 — Test keyboard input and key events

**Starting state:** Fresh page; “Press any key” input is blank and unfocused.

1. Focus the field and type ordinary text.
2. Test representative keys such as Enter, Tab, Escape, and Backspace.
3. Observe displayed values or key-event feedback and verify focus order.

**Expected:** Text and supported key actions are handled consistently; Tab moves focus according to a usable order and does not trap focus. Record whether the widget reports key names, values, or both. Fail on broken keyboard access or page-level shortcuts that unexpectedly disrupt the form.

### AP-21 — Reach the nested Shadow DOM content

**Starting state:** Fresh page; nested Shadow DOM section in its initial state.

1. Locate the nested Shadow DOM section.
2. Inspect its exposed text and controls through normal browser interaction.
3. Activate the “Dummy Booking” link if it is interactive and verify its destination in isolation.

**Expected:** Nested content is rendered and reachable; interactive content responds and links to the expected target. Fail if content is missing or inaccessible to user interaction. Record browser-specific limitations.

### AP-22 — Validate the comment form and required fields

**Starting state:** Fresh page, comment form blank; do not submit real personal information.

1. Locate Name*, Email*, Website, comment/body and save-details controls.
2. Submit with all required values blank.
3. Enter a name but an invalid email and submit.
4. Enter valid-format synthetic name/email, optional website, and comment; submit.
5. Toggle “Save my name, email, and website in this browser” and observe its effect in an isolated context.

**Expected:** Required fields are enforced; malformed email is rejected; valid-format synthetic data is handled with clear confirmation or moderation status; optional website may be omitted. Any persistence behavior follows the checkbox choice. Fail if invalid data is silently accepted or required-field errors are not conveyed.

## Execution notes

- Capture exact option labels, alert text, AJAX messages, and validation copy during the first execution; these are intentionally not invented here.
- Keep destructive or externally navigating actions isolated. Use synthetic inputs and a harmless local fixture for upload.
- Report failures with scenario ID, browser/version, starting state, steps, actual result, expected result, and console/network errors when available.
