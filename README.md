# viewpercapability
Demonstrates the View Per Capability pattern for Android applications

## Introduction

The goal of the view-per-capability pattern is to avoid any kind of isTablet() or isPhone() related actions by putting the device 
specific behavior in separate objects. This increases readability and maintainability since any single class
only deals with the device-specific characteristics.

