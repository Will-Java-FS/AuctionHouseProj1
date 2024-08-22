import '../../../index.css';
import React, { useState, useEffect } from "react";
import { Navbar, NavbarBrand, NavbarContent, NavbarItem, Link, Button } from "@nextui-org/react";
import { ThemeSwitcher } from '../../subcomponents/theme-switcher';
import {Avatar} from "@nextui-org/react";
import { jwtDecode } from 'jwt-decode';
import axios from "axios";
function NavBar() {
    const SITE_NAME = process.env.REACT_APP_SITE_NAME;
    const FALL_BACK_IMAGE = process.env.REACT_APP_FALL_BACK_IMAGE;
    const [img,setImg] = useState(FALL_BACK_IMAGE);
    useEffect(() => {
        const storedToken = localStorage.getItem('token');

        // If no token is found, don't proceed with the API call
        if (!storedToken) return;

        const tokenObject = JSON.parse(storedToken);
        const decodedToken = jwtDecode(storedToken);
        const userId = decodedToken.user_Id;

        axios.get(`http://localhost:8080/user/${userId}`, {
            headers: {
                Authorization: `Bearer ${tokenObject.accessToken}`
            }
        })
        .then(response => {
            if (response.data != null) {
                setImg(response.data.userImage);
            }
        });
    }, []);

    // If no token is found, return null and render nothing
    const storedToken = localStorage.getItem('token');
    if (!storedToken) {
        return null;
    }
    return (
            <Navbar maxWidth="2xl" className="w-full flex content-center px-4">
                {/* Navbar Brand */}
                <NavbarBrand>
                    <Link href="/">
                        <p className="font-bold text-inherit">{SITE_NAME}</p>
                    </Link>
                </NavbarBrand>
                
                {/* Right-Aligned Items */}
                <NavbarContent justify="end" className="flex items-center space-x-4">
                    <NavbarItem>
                        <Avatar src={img} as={Link} href="/account" size="md" />
                    </NavbarItem>
                    <NavbarItem>
                        <Button as={Link} color="primary" href="/login" variant="flat">Login</Button>
                    </NavbarItem>
                    <NavbarItem>
                        <ThemeSwitcher /> {/* ThemeSwitcher aligned to the right */}
                    </NavbarItem>
                </NavbarContent>
            </Navbar>
    );
}

export default NavBar;
