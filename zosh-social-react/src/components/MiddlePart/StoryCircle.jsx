import { Avatar } from '@mui/material'
import React from 'react'

const StoryCircle = () => {
  return (
    <div>
      <div className="flex flex-col items-center mr-4 cursor-pointer">
        <Avatar
          sx={{ width: "5rem", height: "5rem" }}
          src="https://hapotravel.com/wp-content/uploads/2023/03/suu-tam-25-meme-avatar-hai-huoc-va-de-thuong_5.jpg"
        >
        </Avatar>
        <p>PVT</p>
      </div>
    </div>
  )
}

export default StoryCircle