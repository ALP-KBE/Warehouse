import React, {useState} from "react";
import Button from "./Button";

const MainScreen = ({loggedInUser,logOut,components}) => {

    const [allProductComponentsAreShown, setAllProductComponentsAreShown] = useState(false)

    return (

        <div>
            <div className="header">
                <h1>Guitarbuilder</h1>
                <h2>Welcome {loggedInUser}</h2>
                <Button text={'log out'} onClick={logOut}/>
            </div>
            <Button text={'Unsere Produkte'}/>
            <Button text={'Alle Produktkomponenten anzeigen'} onClick={() => {
                setAllProductComponentsAreShown(true)
            }}/>
            <Button text={'Meine Gitarren'}/>
            <Button text={'Währung'}/>

            {allProductComponentsAreShown && <p>{components}</p>}

        </div>
    )
}

export default MainScreen