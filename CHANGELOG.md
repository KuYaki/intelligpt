<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# IntelliGPT Changelog

## [Unreleased]

### Added
- Initial interface for IntelliGPT tool window, featuring an input field and VoiceToggleButton.

### To-Do
- Add hints to buttons.
- Add animation for button transitions (SendButton and VoiceToggleButton).
- Add tests for all the previously done and keep them actual.
- Implement authentication for GPT services.
- Implement GPT functions:
    - Add and remove files in the project.
    - Open specific files.
    - Copy code to clipboard and create diffs with the current code.
    - Request file content.
- Improve GPT prompts to avoid unnecessary information and utilize its functions properly.
- Implement microphone access for voice input.
- Implement voice command processing.
