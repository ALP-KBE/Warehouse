import React, {useState, useEffect} from 'react';
import MainScreen from "./components/MainScreen";
import LogInScreen from "./components/LogInScreen";

const App = () => {
  const [users, setUsers] = useState([{
        username: 'someUser',
        password: 'somePassword'
    },
    {
        username: 'admin',
        password: 'adminPassword'
    }])

  const [loggedIn, setLoggedIn] = useState(false)
  const [loggedInUser, setLoggedInUser] = useState(
      {
          username: '',
      }
  )

  const [components, setComponents] = useState('');

  useEffect(() => {
    fetch('/components')
        .then(response => response.text())
        .then(components => {
            setComponents(components);
        });
  },[])
  return (
      <div>
          {loggedIn && <MainScreen loggedInUser={loggedInUser}
                                    logOut={() => {
                                        setLoggedIn(false)
                                        setLoggedInUser({username: '',})
                                        }
                                    }
                                    components={components}/>}
          {!loggedIn && <LogInScreen users={users} setLoggedIn={setLoggedIn} setLoggedInUser={setLoggedInUser}/>}
      </div>
  )
}

export default App;