# ACLGuard

A Java application for evaluating ACL (Anterior Cruciate Ligament) injury risk based on biomechanical and anatomical factors.

## Overview

ACLGuard collects and analyzes data on key risk factors for ACL tears, including:
- Landing mechanics
- Jump-landing patterns
- Neuromuscular control
- Anatomical measurements
- Demographic information

## Features

- Input and storage of athlete profiles
- Collection of biomechanical assessment data
- Risk analysis based on established factors
- Personalized recommendations for injury prevention
- JSON-based data storage for profiles and assessments

## Getting Started

### Prerequisites
- Java JDK 11 or higher
- JSON library (org.json or Jackson)

### Installation
1. Clone this repository
2. Compile the Java files
3. Run the main application

```bash
javac -d bin src/com/aclguard/*.java
java -cp bin com.aclguard.Main