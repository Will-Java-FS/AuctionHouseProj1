import '../../../index.css';
import React from "react";
import { Navbar, NavbarBrand, NavbarContent, NavbarItem, Link, Button } from "@nextui-org/react";
import { ThemeSwitcher } from '../../subcomponents/ThemeSwitcher';
import {Avatar} from "@nextui-org/react";

function NavBar() {
    const SITE_NAME = process.env.REACT_APP_SITE_NAME;
    const FALL_BACK_IMAGE = process.env.REACT_APP_FALL_BACK_IMAGE;

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
                        <Avatar src={FALL_BACK_IMAGE} as={Link} href="/account" size="md" />
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
