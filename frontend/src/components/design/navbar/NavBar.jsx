import '../../../App.css';
import React from "react";
import {Navbar, NavbarBrand, NavbarContent, NavbarItem, Link, Button} from "@nextui-org/react";


function NavBar() {

    return (
        <Navbar>
            <NavbarBrand>
                <Link href="/">
                    <p className="font-bold text-inherit">Totally Legit Auction</p>
                </Link>
            </NavbarBrand>
            <NavbarContent className="hidden sm:flex gap-4" justify="center">
                <NavbarItem>
                <Link href="/auction">
                    Auction
                </Link>
                </NavbarItem>
                <NavbarItem>
                <Link href="/about">
                    About
                </Link>
                </NavbarItem>
            </NavbarContent>
            <NavbarContent justify="end">
                <NavbarItem className="hidden lg:flex">
                <Link href="/login">Login</Link>
                </NavbarItem>
                <NavbarItem>
                <Button as={Link} color="primary" href="#" variant="flat">
                    Sign Up
                </Button>
                </NavbarItem>
            </NavbarContent>
        </Navbar>
    );
}

export default NavBar;
