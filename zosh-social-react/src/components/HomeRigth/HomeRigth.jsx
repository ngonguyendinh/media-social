import React from 'react';
import SearchUser from '../SearUser/SearchUser';
import PopularUserCard from './PopularUserCard';
import { Card } from '@mui/material';
import NotificationBell from '../notification/notificationbell';
import { useSelector } from 'react-redux';



const popularUser = [1, 1, 1, 1, 1];

const HomeRight = ({ username }) => {
  
  
 
  return (
    <div className="pr-5">
      <div className="flex justify-between items-center mb-4 mt-4">
        <SearchUser />
       
        <NotificationBell username={username} />
      </div>
      <Card className="p-5">
        <div className="flex justify-between py-5 items-center">
          <p className="font-semibold opacity-70">Suggestion for you</p>
          <p className="text-xs font-semibold opacity-95">View All</p>
        </div>
        <div className="space-y-5">
          {popularUser.map((_, index) => (
            <PopularUserCard key={index} />
          ))}
        </div>
      </Card>
    </div>
  );
};

export default HomeRight;
