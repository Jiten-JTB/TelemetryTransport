# Telemetry Data Transfer: A Collaborative Effort for Biomedical Research

## Introduction

This collaborative project, undertaken in partnership with faculty members of Ambedkar DSEU Shakarpur Campus, aims to establish a robust communication channel between a Java GUI and an embedded system for medical data acquisition, configuration, and transfer. Our contributions focus on laying the groundwork for subsequent research conducted by the faculty.

## Objectives

1. **Enable Dynamic Configuration:**
   Configure the embedded system to transmit 16 channels of physiological data, including ECG, EEG, EMG, and EOG, based on user-defined settings.

2. **Facilitate Data Transfer:**
   Utilize TCP sockets to establish a reliable and efficient communication channel for streaming real-time medical data.

3. **Support Future Research:**
   Provide a solid foundation for faculty-led research on embedded system optimization and advanced data visualization using the [Telemetry Viewer](https://github.com/farrellf/TelemetryViewer) tool.

## Project Breakdown

### Sub-projects:

1. **GUI Development (Jiten):**
   - Design and implement a user-friendly Java GUI for configuration management and data transmission control.

2. **Socket Programming (Naman Malik):**
   - Establish TCP socket communication.
   - Manage connections with the embedded system.

### Technology Stack:

- **Programming Language:** Java AWT and Swing
- **Communication Protocol:** TCP sockets
- **Data Visualization Tool:** [Telemetry Viewer](https://github.com/farrellf/TelemetryViewer)

## Getting Started

### Prerequisites:

- Java Development Kit (JDK)
- TelemetryViewer Jar File (v_0.6)

### Build and Run:

1. Clone or download the project repository.
2. Ensure your development environment meets the prerequisites.
3. Download TelemetryViewer Jar File in your root directory.
4. Follow standard Java project setup and execution procedures.

## Usage Guide

1. Launch the `Telemetry.java` application.
2. Define the desired configuration parameters within the GUI interface.
3. Establish a connection with the embedded system by providing its IP address and port number.
4. Initiate data transfer and observe the real-time visualization of physiological data in Telemetry Viewer.
5. For Testing connection you can also use `Server.java` or `Client.java` (Optional)

## Contribution

We welcome contributions to this project! Please follow these guidelines:

1. Fork the repository.
2. Create a new branch for your changes.
3. Implement your modifications and write clear commit messages.
4. Push your changes to your fork and submit a pull request to the main repository.

### Contact

For any inquiries or contribution discussions, please reach out to:

- [**Naman Malik**](https://www.linkedin.com/in/namanmalik18/) 
- [**Jiten**](https://www.linkedin.com/in/jiten-0a7404266/)

## Acknowledgements

We express our gratitude to the faculty members of Ambedkar DSEU Shakarpur Campus for their guidance and support. Additionally, we acknowledge the valuable contribution of the open-source Telemetry Viewer project: [Telemetry Viewer](https://github.com/farrellf/TelemetryViewer).

## Future Developments

This project serves as the initial stage for ongoing research. Future efforts will focus on:

- Enhancing the embedded system's functionalities and performance.
- Exploring advanced data visualization techniques within Telemetry Viewer.
- Integrating further sensors and data modalities for comprehensive medical data acquisition.

We believe this collaborative effort will contribute significantly to advancements in biomedical research and data analysis.
