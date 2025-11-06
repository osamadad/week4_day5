package com.day5.week4_day5_tasks.Controller;

import Api.ApiResponse;
import com.day5.week4_day5_tasks.Model.TasksTracker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/tasksTracker")
public class TasksTrackerController {

    ArrayList<TasksTracker> tasksTrackers= new ArrayList<>();

    @PostMapping("/add")
    public ApiResponse addTask(@RequestBody TasksTracker task){
        this.tasksTrackers.add(task);
        return new ApiResponse("Task have been added successfully");
    }

    @GetMapping("/get")
    public ArrayList<TasksTracker> getTasks(){
        return this.tasksTrackers;
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateTask(@PathVariable int index, @RequestBody TasksTracker task){
        tasksTrackers.set(index,task);
        return new ApiResponse("Task have been updated successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index){
        tasksTrackers.remove(index);
        return new ApiResponse("Task have been deleted successfully");
    }

    @PutMapping("/update/status/{index}")
    public ApiResponse checkTaskStatus(@PathVariable int index){
        if (this.tasksTrackers.get(index).getStatus().equalsIgnoreCase("not done")){
            this.tasksTrackers.get(index).setStatus("done");
        }
        else {
            this.tasksTrackers.get(index).setStatus("not done");
        }
        return new ApiResponse("The task status have been updated to "+this.tasksTrackers.get(index).getStatus());
    }

    @GetMapping("/get/{title}")
    public TasksTracker getTaskByTitle(@PathVariable String title){
        for (TasksTracker task:this.tasksTrackers){
            if (task.getTitle().equals(title)){
                return task;
            }
        }
        return null;
    }

}
