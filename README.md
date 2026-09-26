# AutoWeb Browser

**AutoWeb Browser** is a fully featured, high-performance Chromium-based web browser built specifically for Android, dashboard displays, and driving / vehicle integration. Designed with safety, touch optimization, offline caching, and voice control in mind.

## Creator
- **App Creator**: FourgeAI Labs ([GitHub Profile](https://github.com/fourgeailabs))
- **App Repository**: [AutoWebBrowser GitHub Repo](https://github.com/fourgeailabs/autowebbrowser)

## Key Features
- **Android Auto In-Car Screen Projection**: Native Android for Cars App Library support (`CarAppService`) allowing AutoWeb to be launched directly on connected vehicle head units and center console displays.
- **Driving & In-Motion Safety**: Distraction-optimized templates with oversized touch targets and high-contrast dark mode compliant with driving safety guidelines.
- **Quick-Access Car Dashboard**: Direct one-tap navigation for web search, real-time weather radar, news broadcasts/audio streams, and Wikipedia reference portals.
- **Voice-Command Interface**: Intuitive speech recognition for hands-free searching, navigating, and bookmarking while driving.
- **Full Video & Web Playback**: Hardware-accelerated Chromium WebView engine for responsive web browsing.
- **Session Persistence**: Automatically saves and restores the last open browser page across app restarts and backgrounding.
- **Offline Support**: Cache articles, web pages, and media notes locally in Room DB for access without cellular reception.
- **Customizable Bookmarks**: Instantly save, organize, and access frequently visited sites.

## What's New in Version 1.02.00
- **Android Auto Projection Integration**: Full implementation of Android for Cars App Library (`androidx.car.app:app` & `app-projected`).
- **Vehicle Display App Discovery**: Declared `template` capability in `automotive_app_desc.xml` and registered `AutoCarAppService` in AndroidManifest.
- **In-Vehicle Dashboard Interface**: Interactive glanceable car screen for web portals, search, weather, and instant phone synchronization.
- **Accordion Release History**: Redesigned What's New section into collapsible accordions where opening an update auto-closes previously opened ones.
- **Version 1.01.00 (Historical)**: Enhanced automatic state persistence: saves and restores active browser tab/page when backgrounded, closed, or killed by the system.
- **Version 1.00.00 (Historical)**: Initial production release of AutoWeb Browser, Chromium WebView engine, Room DB offline caching, voice commands, and FourgeAI Labs attribution.

## Android Auto Setup Instructions for Sideloaded APKs
Because this app is installed directly via APK (sideloaded) rather than from the Google Play Store, Android Auto's security settings require enabling "Unknown sources" on the phone for sideloaded car apps to display on the car's screen:
1. On your Android phone, open **Settings** -> **Apps** -> **Android Auto** (or search "Android Auto" in Settings).
2. Scroll to the bottom and tap **Version** 10 times consecutively until a prompt says *"Developer mode enabled"*.
3. Tap the **3 vertical dots menu** in the top right corner and select **Developer settings**.
4. Scroll down and check the box for **"Unknown sources"**.
5. Connect your phone to your car via USB cable or wireless Android Auto. **AutoWeb Browser** will now show up directly on your car's Android Auto dashboard display!

## License
Licensed under the **FourgeAI Labs Proprietary Software License Version 1.0**. See [LICENSE](LICENSE) for details.
