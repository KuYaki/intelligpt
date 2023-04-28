<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# IntelliGPT Changelog

## [Unreleased]

### Added
- Initial interface for IntelliGPT tool window, featuring an input field and VoiceToggleButton.
- Tooltips for buttons, providing helpful hints on their purpose and functionality.
- Token-based authentication for GPT API access.
- Improved user interface for authentication.
- Implemented saving the API key for future use.
- Basic GPT communicator functionality to send messages and store conversation history.
- Updated GPTApi to handle new GPT chat-based API requests and responses.

### To-Do
- Add animation for button transitions (SendButton and VoiceToggleButton).
- Add tests for all the previously done and keep them actual.
- Implement OAuth 2 authentication.
- Implement GPT functions:
  - Add and remove files in the project.
  - Open specific files.
  - Copy code to clipboard and create diffs with the current code.
  - Request file content.
- Improve GPT prompts to avoid unnecessary information and utilize its functions properly.
- Implement microphone access for voice input.
- Implement voice command processing.
- Add the plugin's settings to the general settings in the IDE.
- Implement a dropdown menu on the plugin panel for users to choose the GPT version they want to use.
- Improve message appearance:
  - Custom backgrounds for each message.
  - Handling of HTML tags.
  - Syntax highlighting.
