import json
import os
import uuid
from datetime import datetime
from pathlib import Path

STORAGE_PATH = Path('tasks.json')


def load_tasks():
    if not STORAGE_PATH.exists():
        return []
    with STORAGE_PATH.open('r', encoding='utf-8') as f:
        return json.load(f)

def save_tasks(tasks):
    with STORAGE_PATH.open('w', encoding='utf-8') as f:
        json.dump(tasks, f, indent=2, default=str)

def list_tasks(tasks):
    if not tasks:
        print('No tasks found.')
        return
    print('\nCurrent Tasks:')
    for t in tasks:
        print(f"ID: {t['id']} | Title: {t['title']} | Completed: {t['completed']} | Due: {t.get('due') or '-'} | Tags: {', '.join(t.get('tags', []))}")

def add_task(tasks):
    title = input('Title: ').strip()
    description = input('Description (optional): ').strip()
    tags_line = input('Tags (comma separated, optional): ').strip()
    tags = [tag.strip() for tag in tags_line.split(',')] if tags_line else []
    due_input = input('Due date (YYYY-MM-DD, optional): ').strip()
    due = None
    if due_input:
        try:
            datetime.strptime(due_input, '%Y-%m-%d')
            due = due_input
        except ValueError:
            print('Invalid date format – ignoring due date.')
    task = {
        'id': str(uuid.uuid4()),
        'title': title,
        'description': description or None,
        'tags': tags,
        'due': due,
        'completed': False,
    }
    tasks.append(task)
    save_tasks(tasks)
    print('Task added with ID:', task['id'])

def complete_task(tasks):
    tid = input('Enter task ID to complete: ').strip()
    for t in tasks:
        if t['id'] == tid:
            t['completed'] = True
            save_tasks(tasks)
            print('Task marked as completed.')
            return
    print('Task not found.')

def delete_task(tasks):
    tid = input('Enter task ID to delete: ').strip()
    new_tasks = [t for t in tasks if t['id'] != tid]
    if len(new_tasks) == len(tasks):
        print('Task not found.')
    else:
        save_tasks(new_tasks)
        print('Task deleted.')
    return new_tasks

def filter_by_tag(tasks):
    tag = input('Enter tag to filter by: ').strip().lower()
    filtered = [t for t in tasks if any(tg.lower() == tag for tg in t.get('tags', []))]
    if not filtered:
        print('No tasks with that tag.')
        return
    print(f"\nTasks with tag '{tag}':")
    for t in filtered:
        print(f"ID: {t['id']} | Title: {t['title']} | Completed: {t['completed']} | Due: {t.get('due') or '-'}")

def main():
    print('Welcome to TaskFlow – lightweight CLI task manager (Python version)')
    tasks = load_tasks()
    while True:
        print('\n--- Menu ---')
        print('1) List all tasks')
        print('2) Add a new task')
        print('3) Mark task as complete')
        print('4) Delete a task')
        print('5) Filter tasks by tag')
        print('0) Exit')
        choice = input('Select an option: ').strip()
        if choice == '1':
            list_tasks(tasks)
        elif choice == '2':
            add_task(tasks)
        elif choice == '3':
            complete_task(tasks)
        elif choice == '4':
            tasks = delete_task(tasks) or tasks
        elif choice == '5':
            filter_by_tag(tasks)
        elif choice == '0':
            break
        else:
            print('Invalid selection, try again.')
    print('Goodbye!')

if __name__ == '__main__':
    main()
