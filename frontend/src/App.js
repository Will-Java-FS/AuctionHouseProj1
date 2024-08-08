import React from "react";
import './App.css';
import { NextUIProvider } from "@nextui-org/react";
import AppRouter from './components/AppRouter';
import NavBar from './components/design/navbar/NavBar';
import TestComponent from "./components/TestComponent";

function App() {

  return (
      <NextUIProvider>
        <NavBar />
        <div className="App">
          <header className="App-header">
            <h1>Fancy React App</h1>
            <h2>Totally legit auction house</h2>
            <TestComponent />
            <AppRouter />
          </header>
        </div>
      </NextUIProvider>
  );
}

export default App;