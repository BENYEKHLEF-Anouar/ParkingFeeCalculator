# ParkingFeeCalculator

This is a small Android application built with Jetpack Compose to calculate parking fees based on time slots and rounding modes, while also demonstrating fundamental concepts of UI, state management, and user input. The app allows users to input start and end times (in 24h HH:MM format), select a rounding mode (proportional or full hour), and calculate the parking cost based on configurable time slots. It also displays the total duration, breakdown by slot, and keeps a history of the last three calculations in memory (no persistence).

## Features

- **Time Input**: `TextField` components for entering start and end times in 24h format (HH:MM).
- **Rounding Mode Selection**: Radio buttons to choose between proportional (per minute) or full-hour rounding modes.
- **Parking Fee Calculation**: Calculates the cost by breaking down the duration across configurable time slots (e.g., Night, Day, Evening) with different rates, handling midnight transitions.
- **Result Display**: Shows the total duration, cost breakdown per time slot, and total cost (formatted to 2 decimal places).
- **Calculation History**: Stores and displays the last three calculations (start time, end time, mode, and total cost) in memory.
- **Modern UI**: Styled using Material 3 components in Jetpack Compose for a clean and responsive interface.
- **State Management**: Uses `remember` and `mutableStateListOf` to manage input fields, calculation results, and history, ensuring persistence across screen rotations.

## Technologies Used

- **Kotlin**: The core programming language.
- **Jetpack Compose**: Android's modern, declarative UI toolkit used for building the entire user interface.
- **Material 3**: The design system used for styling components like `TextField`, `Button`, `RadioButton`, and `Card`.
- **Android Studio**: The official IDE for Android development.
  
