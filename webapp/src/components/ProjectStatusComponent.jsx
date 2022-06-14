import React from 'react';
import { Progress } from 'reactstrap';

/// OPTIONS
/// https://6-4-0--reactstrap.netlify.app/components/progress/

const ProjectStatus = (props) => {

  // colors
  // primary | secondary | warning | danger | info

  const states = ["Planned", "Inception", "Elaboration", "Construction", "Transition", "Warranty", "Closed"];

  function ProgressStatus({ data }) {
    let stateActive = true;
    let render = "";
    return !data.length ?
      "ProgressStatus component" : <>
        {data.map((item, i) => {

          render = <Progress
            key={item + i}
            bar
            color={(stateActive == true) ? "primary" : "secondary"}
            value="15"
          >{item}</Progress>;

          if (props.projectStatus.toLowerCase() == item.toLowerCase() && stateActive == true) {
            stateActive = false;
            render = <Progress key={item + i} bar color="warning" value="15">{item}</Progress>;
          }

          return render;

        })}
      </>;
  }

  return (
    <div>
      <Progress multi>
        <ProgressStatus data={states} />
      </Progress>
    </div>
  );
};

export default ProjectStatus;