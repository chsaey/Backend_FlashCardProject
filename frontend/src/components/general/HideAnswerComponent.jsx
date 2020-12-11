import React, { useState } from 'react';

function HideAnswerComponent(props) {
  // Declare a new state variable, which we'll call "count"
  const [answer, setAnswer] = useState(props.answer);

    return (
        <div>
          <button onClick={() => alert(answer)}> 
          Show answer
          </button>
        </div>
      );
  } 


export default HideAnswerComponent;