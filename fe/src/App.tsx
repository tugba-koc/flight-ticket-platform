import { Route, Routes } from 'react-router';
import './App.css';
import AuthLayout from './layouts/AuthLayout/AuthLayout';
import Registration from './components/Registration/Registration';
import Login from './components/Login/Login';
import Flights from './layouts/Flights/Flights';
import Portal from './layouts/Portal/Portal';
import PasswordChange from './components/PasswordChange/PasswordChange';
import BalanceEdit from './components/BalanceEdit/BalanceEdit';
import FlightTickets from './components/FlightTickets/FlightTickets';

function App() {
  return (
    <Routes>
      <Route path='/' element={<AuthLayout />}>
        <Route index element={<Registration />} />
        <Route path='login' element={<Login />} />
      </Route>

      <Route path='/flights' element={<Flights />}>
        {/*         <Route index element={<Registration />} />
        <Route path='login' element={<Login />} /> */}
      </Route>

      <Route path='/portal' element={<Portal />}>
        <Route path='password-change' element={<PasswordChange />} />
        <Route path='balance-edit' element={<BalanceEdit />} />
        <Route path='flight-tickets' element={<FlightTickets />} />
      </Route>
    </Routes>
  );
}

export default App;
