import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { NextUIProvider } from "@nextui-org/react";
import NavBar from './components/design/navbar/NavBar';
import { ThemeProvider as NextThemesProvider } from "next-themes";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <NextUIProvider>
      <NextThemesProvider attribute="class">
        <NavBar />
        <App />
      </NextThemesProvider>
    </NextUIProvider>
  </React.StrictMode>
);

reportWebVitals();
