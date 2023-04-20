# IntelliGPT

<!-- Plugin description -->
IntelliGPT is an IntelliJ IDEA plugin that integrates OpenAI's GPT-powered natural language processing capabilities into your development workflow. It allows you to interact with GPT directly from the IDE, enabling seamless code assistance, navigation, and voice input.
<!-- Plugin description end -->

## Current Status
The current version of IntelliGPT includes a basic user interface with essential elements, such as input fields and buttons. However, there are several crucial features yet to be implemented:

1. ~~**Tooltips for buttons**: Improve the user experience by providing helpful hints on the purpose and functionality of each button in the interface.~~ (Completed)
2. **Button transition animations**: Add smooth animations when transitioning between buttons (e.g., switching from the voice input button to the send button).
3. **Authentication**: Implement a secure authentication mechanism for users to access the GPT API.
   - [x] Implement Token based authentication.
   - [ ] Improve the user interface for authentication.
   - [ ] Implement saving the API key for future use.
   - [ ] Implement OAuth 2 authentication.
4. **GPT functions**: Extend the plugin's capabilities by adding the following GPT-driven features:
   - [ ] Add and remove files in the project.
   - [ ] Open specific files.
   - [ ] Copy code to the clipboard and create diffs between the current code and the clipboard content.
   - [ ] Request the content of a file.
5. **GPT prompts**: Refine GPT's output by crafting efficient prompts, ensuring that it focuses on relevant information and effectively utilizes its available functions.
6. **Microphone access**: Implement microphone access and integration for voice input functionality.
7. **Voice command input**: Allow users to issue voice commands to control the plugin and interact with GPT more efficiently.
8. **Plugin settings**: Add the plugin's settings to the general settings in the IDE, allowing users to customize its behavior according to their preferences.
9. **GPT version selection**: Implement a dropdown menu on the plugin panel for users to choose the GPT version they want to use.
10. **Testing**: Thoroughly test the entire plugin, ensuring that all functionalities work as expected and are well-documented.

## Roadmap
The following roadmap outlines the future development of IntelliGPT:

1. Implement the remaining features listed above to complete the initial version of the plugin.
2. Continuously improve and optimize the user interface based on user feedback.
3. Expand the plugin's functionality and capabilities by incorporating more GPT-driven features.
4. Maintain the plugin by fixing bugs, addressing user issues, and keeping it up-to-date with the latest GPT API changes.

## Contribution
Contributions to IntelliGPT are welcome! If you're interested in contributing, please read our [CONTRIBUTING.md](CONTRIBUTING.md) file for guidelines and instructions.

## License
IntelliGPT is licensed under the [MIT License](LICENSE).
