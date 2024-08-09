const { nextui } = require("@nextui-org/react");

/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,js,jsx,ts,tsx}",
    "./node_modules/@nextui-org/theme/dist/**/*.{js,ts,jsx,tsx}",
  ],
  darkMode: "class",
  theme: {
    extend: {
      colors: {
        foreground: "#333333",   // Dark Gray for text
        background: "#FFF5E6",   // Light Peach for background
        primary: "#FF6600",      // Bright Orange
        secondary: "#FF9933",    // Light Orange
        danger: "#FF3300",       // Dark Orange
        warning: "#FF6600",      // Bright Orange
        success: "#FFCC00",      // Gold
        header: "#003366",       // Dark Blue for headers and navigation
        border: "#CCCCCC",       // Light Gray for borders
      },
      margin: {
        '150px': '150px', // Add a custom spacing value
      },
    },
  },
  plugins: [
    nextui({
      themes: {
        light: {
          colors: {
            primary: {
              DEFAULT: "#FF6600",  // Bright Orange
              foreground: "#FFFFFF",
            },
            secondary: "#FF9933",  // Light Orange
            header: "#003366",    // Dark Blue for headers and navigation
          },
        },
        dark: {
          colors: {
            primary: {
              DEFAULT: "#FF6600",  // Bright Orange
              foreground: "#FFFFFF",
            },
            secondary: "#FF9933",  // Light Orange
            header: "#003366",    // Dark Blue for headers and navigation
          },
        },
      },
    }),
  ],
};
