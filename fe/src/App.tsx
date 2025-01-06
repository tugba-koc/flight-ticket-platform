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
import AddFlight from './layouts/AddFlight/AddFlight';
import Footer from './components/Footer/Footer';
import Loader from './components/Loader/Loader';
import Modal from './components/Modal/Modal';
import FilteredFlightList from './components/FilteredFlightList/FilteredFlightList';

function App() {
  return (
    <div className='App'>
      <Loader />
      <Modal />
      <Routes>
        <Route path='/auth' element={<AuthLayout />}>
          <Route path='register' element={<Registration />} />
          <Route path='login' element={<Login />} />
        </Route>

        <Route path='/' element={<Flights />} />
        <Route path='/flights' element={<FilteredFlightList />} />

        <Route path='/portal' element={<Portal />}>
          <Route index element={<PasswordChange />} />
          <Route path='password-change' element={<PasswordChange />} />
          <Route path='balance-edit' element={<BalanceEdit />} />
          <Route path='flight-tickets' element={<FlightTickets />} />
        </Route>

        <Route path='/add-flight' element={<AddFlight />} />

        <Route path='*' element={<h1>Not Found</h1>} />
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
