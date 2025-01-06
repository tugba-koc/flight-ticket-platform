import React from 'react';

const ImagesRow = () => {
  return (
    <div
      style={{
        display: 'flex',
        justifyContent: 'space-between',
        width: '80%',
        margin: 'auto',
        padding: '18px',
      }}
    >
      <img
        style={{ borderRadius: '10px' }}
        width={300}
        height={200}
        src='https://sediment.com.tr/wp-content/uploads/2024/10/WhatsApp-Image-2024-10-02-at-11.06.02-2-e1727861874432.jpeg'
        alt=''
      />
      <img
        style={{ borderRadius: '10px' }}
        width={300}
        height={200}
        src='https://www.yurtgazetesi.com.tr/d/news/191345.jpg'
        alt=''
      />
      <img
        style={{ borderRadius: '10px' }}
        width={300}
        height={200}
        src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTzcvNcriCLi-SDVQvrjRC8fbMUhkJwx2TVw&s'
        alt=''
      />
    </div>
  );
};

export default ImagesRow;
